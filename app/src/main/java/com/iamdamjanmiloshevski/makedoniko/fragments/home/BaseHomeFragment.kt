package com.iamdamjanmiloshevski.makedoniko.fragments.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iamdamjanmiloshevski.makedoniko.R

/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
abstract class BaseHomeFragment : Fragment() {

    public fun getFragmentView(
        context: Context?,
        viewGroup: ViewGroup?,
        attachToRoot: Boolean,
        savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context).inflate(R.layout.tab_home_child, viewGroup, attachToRoot)
    }
}