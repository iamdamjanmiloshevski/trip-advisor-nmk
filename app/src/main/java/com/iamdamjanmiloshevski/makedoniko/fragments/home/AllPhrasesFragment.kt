package com.iamdamjanmiloshevski.makedoniko.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.iamdamjanmiloshevski.makedoniko.adapters.PhrasesRecyclerViewAdapter
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseHomeFragment
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import com.iamdamjanmiloshevski.makedoniko.viewmodels.HomeChildViewModel
import kotlinx.android.synthetic.main.tab_home_child.view.*

/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class AllPhrasesFragment : BaseHomeFragment() {
    private lateinit var fragmentView: View
    private lateinit var mViewModel: HomeChildViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(HomeChildViewModel::class.java)
        Log.e("name", mViewModel.getName())
        fragmentView.rv_phrases.layoutManager = LinearLayoutManager(context)
        fragmentView.rv_phrases.adapter = PhrasesRecyclerViewAdapter(context!!,mViewModel.getPhrases().value)
        mViewModel.getPhrases()?.observe(this, Observer<List<Phrase>> {
            fragmentView.rv_phrases.adapter?.notifyDataSetChanged()
        })
        return fragmentView
    }

    companion object {
        fun getInstance(): AllPhrasesFragment {
            return AllPhrasesFragment()
        }
    }
}