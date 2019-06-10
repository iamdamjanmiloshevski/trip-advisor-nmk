package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import kotlinx.android.synthetic.main.tab_request_word.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class RequestWordFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = getFragmentView(context, container, false, savedInstanceState)
        fragmentView.tv_title.apply {
            text = "Request word"
        }
        return fragmentView
    }

    override fun getLayoutId(): Int {
        return R.layout.tab_request_word
    }
    companion object{
        fun getInstance():RequestWordFragment{
            return RequestWordFragment()
        }
    }
}