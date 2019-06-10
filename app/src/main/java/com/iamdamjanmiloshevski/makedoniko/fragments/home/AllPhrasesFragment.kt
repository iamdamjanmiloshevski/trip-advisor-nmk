package com.iamdamjanmiloshevski.makedoniko.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseHomeFragment
import com.iamdamjanmiloshevski.makedoniko.viewmodels.HomeChildViewModel

/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class AllPhrasesFragment : BaseHomeFragment() {
    private lateinit var fragmentView: View
    private lateinit var mViewModel: HomeChildViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        mViewModel = getViewModel(activity!!)
        return fragmentView
    }

    companion object {
        fun getInstance(): AllPhrasesFragment {
            return AllPhrasesFragment()
        }
    }
}