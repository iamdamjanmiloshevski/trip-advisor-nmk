package com.iamdamjanmiloshevski.makedoniko.listeners

/** Created by Damjan on 27.6.2019
Project: trip-advisor-nmk
 **/
interface FirebaseLoginListener {
    fun displayError(type: Int)
    fun displayGoogleSignInFailed(type:Int)
}