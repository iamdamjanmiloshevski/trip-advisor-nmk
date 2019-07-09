package com.iamdamjanmiloshevski.makedoniko.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.listeners.FirebaseLoginListener
import com.iamdamjanmiloshevski.makedoniko.listeners.ScreenListener
import com.iamdamjanmiloshevski.makedoniko.managers.FirebaseLoginManager
import com.iamdamjanmiloshevski.makedoniko.utils.LoginUtils
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : BaseActivity(), FirebaseLoginListener, ScreenListener {
    private var cancel = false
    override fun displayError(type: Int) {

    }

    override fun displayGoogleSignInFailed(type: Int) {

    }

    override fun displayLoadingBar() {

    }

    override fun hideLoadingBar() {

    }

    override fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun openMain() {

    }

    override fun initListeners() {
        et_email.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                til_email.error = null
                cancel = false
            }


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        et_new_password.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                til_new_password.error = null
                cancel = false
            }


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        et_confirm_new_password.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                til_confirm_pwd.error = null
                cancel = false
            }


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.confirm_pass_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_save -> {
                changePassword()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun changePassword() {
        val newPassword = et_new_password.text.toString()
        val newPasswordConfirm = et_confirm_new_password.text.toString()
        val email = et_email.text.toString()
        if (TextUtils.isEmpty(newPassword) && TextUtils.isEmpty(newPasswordConfirm) && TextUtils.isEmpty(email)) {
            til_new_password.error = "This field must not be blank"
            til_confirm_pwd.error = "This field must not be blank"
            til_email.error = "This field must not be blank"
            cancel = true
        } else if (newPassword != newPasswordConfirm) {
            til_new_password.error = "Passwords don't match"
            til_confirm_pwd.error = "Passwords don't match"
            cancel = true
        } else if (newPassword.length < 6) {
            til_new_password.error = "New password is too short"
            cancel = true
        } else if (!LoginUtils.validateEmail(email)) {
            til_email.error = "Please enter an email in valid format"
            cancel = true
        } else if (TextUtils.isEmpty(newPassword)) {
            til_new_password.error = "New Password must not be blank"
            cancel = true
        } else if (TextUtils.isEmpty(newPasswordConfirm)) {
            til_confirm_pwd.error = "This field must not be blank"
            cancel = true
        } else {
            if (!cancel) {
                FirebaseLoginManager.getInstance(this).changePassword(
                    this, email
                )
            }
        }
    }
}
