package com.iamdamjanmiloshevski.makedoniko.fragments.home

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.adapters.ApplicationViewPagerAdapter
import com.iamdamjanmiloshevski.makedoniko.fragments.BaseFragment
import kotlinx.android.synthetic.main.tab_home.view.*

/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class HomeFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.tab_home
    }

    private lateinit var mAdapter: ApplicationViewPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = getFragmentView(context, container, false, savedInstanceState)
        mAdapter = ApplicationViewPagerAdapter(fragmentManager)
        mAdapter.addFragment(GreetingsFragment.getInstance(), "Greetings")
        mAdapter.addFragment(MostUsedPhrasesFragment.getInstance(), "Most Used")
        mAdapter.addFragment(UsefulPhrasesFragment.getInstance(), "Useful phrases")
        view.vp_home_pages.adapter = mAdapter
        view.tv_home_tabs.setupWithViewPager(view.vp_home_pages)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}