package com.iamdamjanmiloshevski.makedoniko.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.firestore.FirebaseFirestore
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.managers.GoogleAuthManager
import com.iamdamjanmiloshevski.makedoniko.managers.LoginManager
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.RC_SIGN_IN

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    private lateinit var googleAuthManager: GoogleAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        googleAuthManager = GoogleAuthManager.getInstance(applicationContext)
        initListeners()
    }

    private fun initListeners() {
        btn_google_sign_in.setOnClickListener { v ->
            googleAuthManager.googleSignIn(this)
        }
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
                googleAuthManager.firebaseAuthWithGoogle(applicationContext, account!!)
            } catch (e: ApiException) {
                Timber.e(e, "Google sign in failed")
            }
        }
    }

}
