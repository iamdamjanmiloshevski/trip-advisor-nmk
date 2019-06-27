package com.iamdamjanmiloshevski.makedoniko.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

/** Created by Damjan on 27.6.2019
Project: trip-advisor-nmk
 **/
object LoginUtils {
    const val EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"
    private val pattern = Pattern.compile(EMAIL_PATTERN)
    private var matcher: Matcher? = null

    fun validateEmail(email: String): Boolean {
        matcher = pattern.matcher(email)
        return matcher!!.matches()
    }

    fun getPasswordError(password: String, confirmPassword: String): String {
        if (password != confirmPassword) {
            return "Passwords don't match"
        } else if (password.length < 6) {
            return "Password too short"
        }
        return ""
    }
}