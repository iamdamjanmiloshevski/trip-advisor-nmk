package com.iamdamjanmiloshevski.makedoniko.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.tab_home_child.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class MostUsedPhrasesFragment : BaseHomeFragment() {
    private lateinit var fragmentView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        fragmentView.title.apply {
            text = "Most Used Words Fragment"
        }
        return fragmentView
    }

    companion object {
        fun getInstance(): MostUsedPhrasesFragment {
            return MostUsedPhrasesFragment()
        }
    }
}