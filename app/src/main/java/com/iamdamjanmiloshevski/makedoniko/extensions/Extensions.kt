package com.iamdamjanmiloshevski.makedoniko.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

/** Created by Damjan on 26.6.2019
Project: trip-advisor-nmk
 **/

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}
