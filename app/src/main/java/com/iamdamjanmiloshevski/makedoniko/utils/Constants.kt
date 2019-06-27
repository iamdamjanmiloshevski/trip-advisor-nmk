package com.iamdamjanmiloshevski.makedoniko.utils

/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
object Constants {
    //UI constants
    const val HOME_PAGES = 4
    //Repository constants
    const val PHRASES_COLLECTION = "phrases"
    const val CATEGORY = "category"
    const val LANDMARKS_COLLECTION = "landmarks"
    const val CITY_NAME = "cityName"
    const val USERS = "users"

    //Google Sign-In
    const val RC_SIGN_IN = 300

    //Session
    const val GOOGLE_SIGN_IN = "google_sign_in"
    const val IS_SIGNED_IN = "is_signed_in"

    //Errors
    const val GOOGLE_SIGN_FAILED = 1000
    const val FIREBASE_LOGIN_ERROR_NO_PASSWORD = 1001
    const val FIREBASE_LOGIN_ERROR_NO_SUCH_USER = 1002
}