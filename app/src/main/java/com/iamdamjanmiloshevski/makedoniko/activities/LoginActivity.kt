package com.iamdamjanmiloshevski.makedoniko.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.extensions.showToast
import com.iamdamjanmiloshevski.makedoniko.listeners.FirebaseLoginListener
import com.iamdamjanmiloshevski.makedoniko.managers.FirebaseLoginManager
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_LOGIN_ERROR_NO_PASSWORD
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_LOGIN_ERROR_NO_SUCH_USER
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.RC_SIGN_IN

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import timber.log.Timber

class LoginActivity : AppCompatActivity(), OnClickListener, FirebaseLoginListener {
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
        }
    }

    private fun login() {
        val email = et_email.text.toString()
        val pwd = et_pwd.text.toString()
        FirebaseLoginManager.getInstance(this).signIn(this, email, pwd, this)
    }

    private lateinit var firebaseLoginManager: FirebaseLoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        firebaseLoginManager = FirebaseLoginManager.getInstance(this)
        initListeners()
        loadLink()
    }

    private fun loadLink() {
        val textLink = "<html>Don't have an account? Register <a style href=\"\">here</a></html>"
        tv_register.text = HtmlCompat.fromHtml(textLink, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tv_register.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun initListeners() {
        btn_google_sign_in.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_email.error = null
            }

        })
        et_pwd.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_password.error = null
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
                firebaseLoginManager.firebaseAuthWithGoogle(applicationContext, account!!, this)
            } catch (e: ApiException) {
                Timber.e(e, "Google sign in failed")
            }
        }
    }

}
