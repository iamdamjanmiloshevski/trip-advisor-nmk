package com.iamdamjanmiloshevski.makedoniko.managers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.activities.LoginActivity
import com.iamdamjanmiloshevski.makedoniko.activities.MainActivity
import com.iamdamjanmiloshevski.makedoniko.extensions.showToast
import com.iamdamjanmiloshevski.makedoniko.listeners.FirebaseLoginListener
import com.iamdamjanmiloshevski.makedoniko.listeners.ScreenListener
import com.iamdamjanmiloshevski.makedoniko.models.User
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_LOGIN_ERROR_EMAIL_BAD_FORMAT
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_LOGIN_ERROR_NO_PASSWORD
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_LOGIN_ERROR_NO_SUCH_USER
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.FIREBASE_SIGNUP_ERROR_EMAIL_ALREADY_USED
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.GOOGLE_SIGN_FAILED
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.RC_SIGN_IN
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.USERS
import timber.log.Timber

/** Created by Damjan on 25.6.2019
Project: trip-advisor-nmk
 **/
class FirebaseLoginManager(context: Context) {
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.project_web_client_id))
        .requestEmail()
        .build()
    private val googleSignInClient: GoogleSignInClient
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    init {
        googleSignInClient = GoogleSignIn.getClient(context, gso)
        if (googleSignInClient != null) {
            Timber.i("Google Sign In Client initialized")
        }
    }

    fun googleSignIn(activity: Activity) {
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun firebaseAuthWithGoogle(
        context: Context,
        account: GoogleSignInAccount,
        listener: FirebaseLoginListener,
        screenListener: ScreenListener
    ) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listener.displayLoadingBar()
                val user = auth.currentUser
                if (user != null) {
                    SharedPreferencesManager.getInstance(context).setIsLoggedIn(true)
                    SharedPreferencesManager.getInstance(context).setIsLoggedWithGoogle(true)
                    SharedPreferencesManager.getInstance(context).setUserId(user.uid)
                    firestore.collection(USERS).document(user.uid).get().addOnCompleteListener {
                        if (it.result!!.exists()) {
                            context.showToast("Signed in successfully", Toast.LENGTH_SHORT)
                            screenListener.openMain()
                        } else {
                            //new user - first login - create it in the db
                            val userFirestore = User(
                                user.uid,
                                user.email as String,
                                user.displayName as String,
                                user.photoUrl.toString()
                            )
                            firestore.collection(USERS).document(user.uid).set(userFirestore.getMap(userFirestore))
                                .addOnSuccessListener {
                                    context.showToast("Signed in successfully", Toast.LENGTH_SHORT)
                                    screenListener.openMain()
                                }
                        }
                    }
                }
            }
        }.addOnFailureListener { e ->
            listener.hideLoadingBar()
            listener.displayGoogleSignInFailed(GOOGLE_SIGN_FAILED)
        }
    }

    fun signIn(context: Context, email: String, password: String, listener: FirebaseLoginListener) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            listener.displayLoadingBar()
            if (task.isSuccessful) {
                //Sign in successful
                SharedPreferencesManager.getInstance(context).setIsLoggedIn(true)
                context.startActivity(Intent(context, MainActivity::class.java))
            }
        }.addOnFailureListener { e ->
            when (e.localizedMessage) {
                "The password is invalid or the user does not have a password." ->
                    listener.displayError(FIREBASE_LOGIN_ERROR_NO_PASSWORD)
                "There is no user record corresponding to this identifier. The user may have been deleted." ->
                    listener.displayError(FIREBASE_LOGIN_ERROR_NO_SUCH_USER)
                "The email address is badly formatted." ->
                    listener.displayError(FIREBASE_LOGIN_ERROR_EMAIL_BAD_FORMAT)
            }
            listener.hideLoadingBar()
            Timber.e(e.localizedMessage)
        }
    }

    fun signUp(
        context: Context,
        fullName: String,
        email: String,
        password: String,
        listener: FirebaseLoginListener,
        screenListener: ScreenListener
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            listener.displayLoadingBar()
            if (task.isSuccessful) {
                val firebaseUser = auth.currentUser
                if (firebaseUser != null) {
                    val user = User(firebaseUser.uid, fullName, email, firebaseUser.photoUrl.toString())
                    firestore.collection(USERS).document(firebaseUser.uid).set(user.getMap(user)).addOnSuccessListener {
                        SharedPreferencesManager.getInstance(context).setIsLoggedIn(true)
                        context.showToast("Registered succesfully You can sign in now!", Toast.LENGTH_SHORT)
                        screenListener.openLogin()
                        listener.hideLoadingBar()
                    }
                }
            } else {
                listener.hideLoadingBar()
                context.showToast("Sign up failed. Please try again later!", Toast.LENGTH_SHORT)
            }

        }.addOnFailureListener { e ->
            when (e.localizedMessage) {
                "The email address is already in use by another account." -> listener.displayError(
                    FIREBASE_SIGNUP_ERROR_EMAIL_ALREADY_USED
                )
            }
            listener.hideLoadingBar()
            Timber.e(e.localizedMessage)
        }
    }

    fun logout(context: Context, isLoggedInWithGoogle: Boolean, listener: ScreenListener) {
        if (isLoggedInWithGoogle) {
            //Firebase sign out
            auth.signOut()
            //Google sign out
            googleSignInClient.signOut().addOnCompleteListener {
                SharedPreferencesManager.getInstance(context).setIsLoggedWithGoogle(false)
                SharedPreferencesManager.getInstance(context).setIsLoggedIn(false)
                listener.openLogin()
            }
        } else {
            auth.signOut()
            SharedPreferencesManager.getInstance(context).setIsLoggedIn(false)
            listener.openLogin()
        }
    }

    fun changePassword(email: String, newPassword: String) {
        val credential = EmailAuthProvider.getCredential(email, newPassword)
        val auth = FirebaseAuth.getInstance()
        auth.currentUser!!.reauthenticate(credential).addOnSuccessListener {

        }.addOnFailureListener { e ->
            Timber.e(e)
        }
    }

    companion object {
        private var INSTANCE: FirebaseLoginManager? = null

        fun getInstance(context: Context): FirebaseLoginManager {
            if (INSTANCE == null) {
                INSTANCE = FirebaseLoginManager(context)
            }
            return INSTANCE as FirebaseLoginManager
        }
    }
}