package com.iamdamjanmiloshevski.makedoniko.managers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.firestore.FirebaseFirestore
import com.iamdamjanmiloshevski.makedoniko.activities.MainActivity
import com.iamdamjanmiloshevski.makedoniko.models.User
import timber.log.Timber

/** Created by Damjan on 25.6.2019
Project: trip-advisor-nmk
 **/
class LoginManager(context: Context) {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        private var INSTANCE: LoginManager? = null
        fun getInstance(context: Context): LoginManager {
            if (INSTANCE == null) {
                INSTANCE = LoginManager(context)
            }
            return INSTANCE as LoginManager
        }
    }
}