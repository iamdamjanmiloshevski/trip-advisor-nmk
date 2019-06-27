package com.iamdamjanmiloshevski.makedoniko.managers

import android.content.Context
import android.content.SharedPreferences
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.GOOGLE_SIGN_IN
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.IS_SIGNED_IN

/** Created by Damjan on 26.6.2019
Project: trip-advisor-nmk
 **/
class SharedPreferencesManager(context: Context) {
    private var sharedPrefs: SharedPreferences? = null

    init {
        sharedPrefs = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    }

    fun setIsLoggedWithGoogle(isLoggedInWithGoogle: Boolean) {
        val editor = sharedPrefs!!.edit()
        editor.putBoolean(GOOGLE_SIGN_IN, isLoggedInWithGoogle).apply()
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        val editor = sharedPrefs!!.edit()
        editor.putBoolean(IS_SIGNED_IN, isLoggedIn).apply()
    }

    fun isLoggedInWithGoogle(): Boolean {
        return sharedPrefs!!.getBoolean(GOOGLE_SIGN_IN, false)
    }

    fun isSignedIn(): Boolean {
        return sharedPrefs!!.getBoolean(IS_SIGNED_IN, false)
    }

    companion object {
        const val SHARED_PREF = "app_session"
        private var INSTANCE: SharedPreferencesManager? = null
        fun getInstance(context: Context): SharedPreferencesManager {
            if (INSTANCE == null) {
                INSTANCE = SharedPreferencesManager(context)
            }
            return INSTANCE as SharedPreferencesManager
        }
    }
}