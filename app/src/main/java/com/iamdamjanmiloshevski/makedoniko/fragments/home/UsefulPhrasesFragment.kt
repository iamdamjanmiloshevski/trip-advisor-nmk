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
import com.iamdamjanmiloshevski.makedoniko.viewmodels.BaseChildViewModel
import com.iamdamjanmiloshevski.makedoniko.viewmodels.UsefulPhrasesViewModel
import kotlinx.android.synthetic.main.tab_home_child.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class UsefulPhrasesFragment : BaseHomeFragment() {
    /**
     * Called when the data is changed.
     * @param data  The new data
     */
    override fun onChanged(data: List<Phrase>?) {
       fragmentView.progressbar.visibility=View.VISIBLE
        data.let {
            phrases.addAll(it as List<Phrase>)
            Log.i(TAG, "${phrases.size}")
            if (phrases.isNotEmpty()) {
                fragmentView.rv_phrases.adapter?.notifyDataSetChanged()
                fragmentView.progressbar.visibility = View.GONE
                showNoData(false, fragmentView.rv_phrases, fragmentView.tv_no_data)
            } else {
                showNoData(true, fragmentView.rv_phrases, fragmentView.tv_no_data)
            }
        }
    }

    override fun removeObservers() {
        mViewModel.getPhrasesByCategory(2).removeObserver(this)
    }

    override fun initComponents() {
        mViewModel = ViewModelProviders.of(activity!!).get(UsefulPhrasesViewModel::class.java)
        fragmentView.rv_phrases.layoutManager = LinearLayoutManager(context)
        fragmentView.rv_phrases.adapter = PhrasesRecyclerViewAdapter(context!!, phrases)
        fragmentView.progressbar.visibility = View.VISIBLE
        if(phrases.isEmpty()){
            fragmentView.progressbar.visibility=View.GONE
            showNoData(true, fragmentView.rv_phrases, fragmentView.tv_no_data)
        }
    }

    override fun registerObservers() {
        mViewModel.getPhrasesByCategory(2).observe(this, this)
    }

    private lateinit var fragmentView: View
    private var phrases = mutableListOf<Phrase>()
    private lateinit var mViewModel: UsefulPhrasesViewModel
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
        const val TAG = "UsefulPhrasesFragment"
        fun getInstance(): UsefulPhrasesFragment {
            return UsefulPhrasesFragment()
        }
    }
}