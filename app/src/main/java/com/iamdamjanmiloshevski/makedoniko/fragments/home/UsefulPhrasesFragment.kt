package com.iamdamjanmiloshevski.makedoniko.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseHomeFragment


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class UsefulPhrasesFragment : BaseHomeFragment() {
    private lateinit var fragmentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        fragmentView.title.apply {
            text="UsefulPhrases Fragment"
        }
        return fragmentView
    }
    companion object {
        fun getInstance(): UsefulPhrasesFragment{
            return UsefulPhrasesFragment()
        }
    }
}