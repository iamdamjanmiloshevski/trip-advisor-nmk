package com.iamdamjanmiloshevski.makedoniko.fragments.main

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.adapters.ApplicationViewPagerAdapter
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.home.AllPhrasesFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.home.GreetingsFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.home.MostUsedPhrasesFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.home.UsefulPhrasesFragment
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.HOME_PAGES
import kotlinx.android.synthetic.main.activity_main.*
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
        initAdapter()
        view.vp_home_pages.adapter = mAdapter
        view.vp_home_pages.offscreenPageLimit = HOME_PAGES
        view.tv_home_tabs.setupWithViewPager(view.vp_home_pages)
        return view
    }

    private fun initAdapter() {
        mAdapter = ApplicationViewPagerAdapter(childFragmentManager)
        mAdapter.addFragment(AllPhrasesFragment.getInstance(), getString(R.string.all_phrases_fragment_title))
        mAdapter.addFragment(GreetingsFragment.getInstance(), getString(R.string.greetings_fragment_title))
        mAdapter.addFragment(MostUsedPhrasesFragment.getInstance(), getString(R.string.most_used_fragment_title))
        mAdapter.addFragment(UsefulPhrasesFragment.getInstance(), getString(R.string.useful_phrases_fragment_title))
    }

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}