package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import kotlinx.android.synthetic.main.tab_account.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class AccountFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = getFragmentView(context, container, false, savedInstanceState)
        fragmentView.tv_title.apply {
            text = "Account"
        }
        return fragmentView
    }

    override fun getLayoutId(): Int {
        return R.layout.tab_account
    }

    companion object {
        fun getInstance(): AccountFragment {
            return AccountFragment()
        }
    }
}