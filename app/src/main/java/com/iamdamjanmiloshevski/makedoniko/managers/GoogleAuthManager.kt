package com.iamdamjanmiloshevski.makedoniko.managers

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.activities.MainActivity
import com.iamdamjanmiloshevski.makedoniko.models.User
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.RC_SIGN_IN
import timber.log.Timber

/** Created by Damjan on 25.6.2019
Project: trip-advisor-nmk
 **/
class GoogleAuthManager(context: Context) {
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

    fun firebaseAuthWithGoogle(context: Context, account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                firestore.collection("users").document(user!!.uid).get().addOnSuccessListener {
                    if (task.isSuccessful) {
                        if (it != null) {
                            val u = it.toObject(User::class.java)
                            Toast.makeText(context, "Already logged in", Toast.LENGTH_SHORT).show()
                        } else {
                            val u = User(user.email as String, "")
                            firestore.collection("users").document(user!!.uid).set(u.getMap(u)).addOnSuccessListener {
                                (context as Activity).startActivity(Intent(context, MainActivity::class.java))
                            }
                        }
                    }
                }.addOnFailureListener { exception ->
                    Timber.e(exception)
                }
            } else {

            }
        }
    }

    companion object {
        private var INSTANCE: GoogleAuthManager? = null

        fun getInstance(context: Context): GoogleAuthManager {
            if (INSTANCE == null) {
                INSTANCE = GoogleAuthManager(context)
            }
            return INSTANCE as GoogleAuthManager
        }
    }
}