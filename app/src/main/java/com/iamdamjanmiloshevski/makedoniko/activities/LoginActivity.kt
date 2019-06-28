package com.iamdamjanmiloshevski.makedoniko.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.extensions.showToast
import com.iamdamjanmiloshevski.makedoniko.listeners.FirebaseLoginListener
import com.iamdamjanmiloshevski.makedoniko.listeners.ScreenListener
import com.iamdamjanmiloshevski.makedoniko.managers.FirebaseLoginManager
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_LOGIN_ERROR_EMAIL_BAD_FORMAT
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_LOGIN_ERROR_NO_PASSWORD
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_LOGIN_ERROR_NO_SUCH_USER
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.RC_SIGN_IN

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import timber.log.Timber

class LoginActivity : BaseActivity(), OnClickListener, FirebaseLoginListener, ScreenListener {
    override fun openMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun openLogin() {
        //not used
    }

    override fun displayLoadingBar() {
        progressbar.visibility = VISIBLE
        lyt_data.visibility = GONE
    }

    override fun hideLoadingBar() {
        progressbar.visibility = GONE
        lyt_data.visibility = VISIBLE
    }

    private var cancel = false
    override fun displayGoogleSignInFailed(type: Int) {
        showToast("Google Sign in failed.Please try again later")
    }

    override fun displayError(type: Int) {
        when (type) {
            FIREBASE_LOGIN_ERROR_NO_SUCH_USER -> showToast("No such user!")
            FIREBASE_LOGIN_ERROR_NO_PASSWORD -> {
                til_email.error = "Invalid credentials"
                til_password.error = "Invalid credentials"
            }
            FIREBASE_LOGIN_ERROR_EMAIL_BAD_FORMAT -> til_email.error = "The email address is badly formatted"
        }
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_google_sign_in -> firebaseLoginManager.googleSignIn(this)
            R.id.btn_login -> login()
            R.id.tv_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
            R.id.tv_reset_pass -> startActivity(Intent(this,ChangePasswordActivity::class.java))
        }
    }

    private fun login() {
        val email = et_email.text.toString()
        val pwd = et_pwd.text.toString()
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd)) {
            cancel = true
            til_email.error = "Email must not be blank"
            til_password.error = "Password must not be blank"
        } else if (TextUtils.isEmpty(email)) {
            cancel = true
            til_email.error = "Email must not be blank"
        } else if (TextUtils.isEmpty(pwd)) {
            cancel = true
            til_password.error = "Password must not be blank"
        } else {
            if (!cancel) {
                FirebaseLoginManager.getInstance(this).signIn(this, email, pwd, this)
            }
        }
    }

    private lateinit var firebaseLoginManager: FirebaseLoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        firebaseLoginManager = FirebaseLoginManager.getInstance(this)
        initListeners()
        loadLinks()
    }

    private fun loadLinks() {
        val textLink = "<html>Don't have an account? Register <a style href=\"\">here</a></html>"
        tv_register.text = HtmlCompat.fromHtml(textLink, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tv_register.movementMethod = LinkMovementMethod.getInstance()
        val resetPassLink = "<html>Forgot your password? Reset it <a href=\"\">here</a></html>"
        tv_reset_pass.text = HtmlCompat.fromHtml(resetPassLink, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tv_reset_pass.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun initListeners() {
        btn_google_sign_in.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        tv_reset_pass.setOnClickListener(this)
        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_email.error = null
                cancel = false
            }

        })
        et_pwd.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_password.error = null
                cancel = false
            }

        })
        tv_register.setOnClickListener(this)
    }

    /**
     * Dispatch incoming result to the correct fragment.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((resultCode == Activity.RESULT_OK) && requestCode == RC_SIGN_IN && data != null) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseLoginManager.firebaseAuthWithGoogle(applicationContext, account!!, this, this)
            } catch (e: ApiException) {
                Timber.e(e, "Google sign in failed")
            }
        }
    }

}
