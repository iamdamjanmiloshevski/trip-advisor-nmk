package com.iamdamjanmiloshevski.makedoniko.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.text.HtmlCompat
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.listeners.FirebaseLoginListener
import com.iamdamjanmiloshevski.makedoniko.listeners.ScreenListener
import com.iamdamjanmiloshevski.makedoniko.managers.FirebaseLoginManager
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_SIGNUP_ERROR_EMAIL_ALREADY_USED
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.et_email
import kotlinx.android.synthetic.main.activity_register.til_email
import kotlinx.android.synthetic.main.activity_register.til_password

class RegisterActivity : BaseActivity(), View.OnClickListener, FirebaseLoginListener, ScreenListener {
    override fun openMain() {
        //not used
    }

    override fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private var cancel = false
    override fun displayLoadingBar() {
        progressbar.visibility = VISIBLE
        lyt_data.visibility = GONE
    }

    override fun hideLoadingBar() {
        progressbar.visibility = GONE
        lyt_data.visibility = VISIBLE
    }

    override fun initListeners() {
        btn_register.setOnClickListener(this)
        et_email.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                til_email.error = null
                cancel = false
            }

        })
        et_password.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                til_password.error = null
                cancel = false
            }

        })
        et_confirm_password.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                til_confirm_pwd.error = null
                cancel = false
            }

        })
        et_full_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_full_name.error = null
                cancel = false
            }

        })
        cb_terms.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                btn_register.visibility = VISIBLE
            } else {
                btn_register.visibility = GONE
            }
        }
        tv_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_register -> register()
            R.id.tv_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun register() {
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        val confirmPassword = et_confirm_password.text.toString()
        val fullName = et_full_name.text.toString()
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)
            && TextUtils.isEmpty(confirmPassword) && TextUtils.isEmpty(fullName)
        ) {
            til_email.error = "Email must not be blank"
            til_password.error = "Password must not be blank"
            til_confirm_pwd.error = "You must confirm your password"
            til_full_name.error = "Name must not be blank"
            cancel = true
        } else if (password != confirmPassword) {
            til_password.error = "Passwords don't match"
            til_confirm_pwd.error = "Passwords don't match"
            cancel = true
        } else if (TextUtils.isEmpty(password)) {
            til_password.error = "Password cannot be empty"
            cancel = true
        } else if (TextUtils.isEmpty(confirmPassword)) {
            til_confirm_pwd.error = "You must confirm your password"
            cancel = true
        } else if (TextUtils.isEmpty(fullName)) {
            til_full_name.error = "Name must not be blank"
            cancel = true
        } else if (password.length < 6) {
            til_password.error = "Password too short"
            cancel = true
        } else {
            FirebaseLoginManager.getInstance(this).signUp(this, fullName, email, password, this,
                this)
        }
    }

    override fun displayError(type: Int) {
        when (type) {
            FIREBASE_SIGNUP_ERROR_EMAIL_ALREADY_USED -> {
                til_email.error = "The email address is already in use by another account"
                cancel = true
            }
        }
    }

    override fun displayGoogleSignInFailed(type: Int) {
        //not used
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initListeners()
        loadLinks()
    }

    private fun loadLinks() {
        val textLink =
            "<html>I hereby accept the <a style href=\"https://www.lipsum.com/\">Terms of Use</a> & <a href=\"https://www.lipsum.com/\">Privacy Policy</html>"
        cb_terms.text = HtmlCompat.fromHtml(textLink, HtmlCompat.FROM_HTML_MODE_LEGACY)
        cb_terms.movementMethod = LinkMovementMethod.getInstance()
        val loginLink = "<html>Already have an account? Login <a href=\"\">here</a></html>"
        tv_login.text = HtmlCompat.fromHtml(loginLink, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tv_login.movementMethod = LinkMovementMethod.getInstance()
    }
}
