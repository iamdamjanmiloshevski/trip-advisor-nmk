package com.iamdamjanmiloshevski.makedoniko.activities

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/** Created by Damjan on 28.6.2019
Project: trip-advisor-nmk
 **/
abstract class BaseActivity :AppCompatActivity(){
    abstract fun initListeners()
}