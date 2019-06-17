package com.iamdamjanmiloshevski.makedoniko.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.iamdamjanmiloshevski.makedoniko.adapters.PhrasesRecyclerViewAdapter
import com.iamdamjanmiloshevski.makedoniko.fragments.bases.BaseHomeFragment
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import com.iamdamjanmiloshevski.makedoniko.viewmodels.AllPhrasesViewModel
import kotlinx.android.synthetic.main.tab_home_child.view.*

/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class AllPhrasesFragment : BaseHomeFragment() {
    override fun registerObservers() {
        mViewModel.getPhrases().observe(this, this)
    }

    override fun initComponents() {
        mViewModel = ViewModelProviders.of(activity!!).get(AllPhrasesViewModel::class.java)
        fragmentView.rv_phrases.layoutManager = LinearLayoutManager(context)
        fragmentView.rv_phrases.adapter = PhrasesRecyclerViewAdapter(context!!, phrases)
        fragmentView.progressbar.visibility = View.VISIBLE
    }

    override fun removeObservers() {
        mViewModel.getPhrases().removeObserver(this)
    }

    /**
     * Called when the data is changed.
     * @param data  The new data
     */
    override fun onChanged(data: List<Phrase>?) {
        fragmentView.progressbar.visibility = View.VISIBLE
        getData(
            fragmentView.rv_phrases,
            fragmentView.rv_phrases.adapter as PhrasesRecyclerViewAdapter,
            fragmentView.tv_no_data,
            fragmentView.progressbar,
            data,
            phrases
        )
    }

    private lateinit var fragmentView: View
    private lateinit var mViewModel: AllPhrasesViewModel
    private var phrases = mutableListOf<Phrase>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        initComponents()
        registerObservers()
        return fragmentView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

    companion object {
        const val TAG = "AllPhrasesFragment"
        fun getInstance(): AllPhrasesFragment {
            return AllPhrasesFragment()
        }
    }
}