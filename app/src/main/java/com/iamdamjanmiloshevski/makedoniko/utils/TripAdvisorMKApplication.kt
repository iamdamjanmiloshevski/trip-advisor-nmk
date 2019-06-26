package com.iamdamjanmiloshevski.makedoniko.utils

import android.app.Application
import com.iamdamjanmiloshevski.makedoniko.BuildConfig
import timber.log.Timber


/** Created by Damjan on 25.6.2019
Project: trip-advisor-nmk
 **/
class TripAdvisorMKApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}