package com.iamdamjanmiloshevski.makedoniko.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseHomeFragment


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class GreetingsFragment : BaseHomeFragment() {
    private lateinit var fragmentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)

        return fragmentView
    }
    companion object {
        fun getInstance(): GreetingsFragment{
            return GreetingsFragment()
        }
    }
}