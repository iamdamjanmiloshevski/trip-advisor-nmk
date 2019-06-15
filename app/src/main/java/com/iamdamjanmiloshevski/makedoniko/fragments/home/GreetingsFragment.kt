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
import com.iamdamjanmiloshevski.makedoniko.viewmodels.GreetingsPhrasesViewModel
import kotlinx.android.synthetic.main.tab_home_child.view.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
class GreetingsFragment : BaseHomeFragment() {
    override fun removeObservers() {
        mViewModel.getPhrasesByCategory(0).removeObserver(this)
    }

    /**
     * Called when the data is changed.
     * @param data  The new data
     */
    override fun onChanged(data: List<Phrase>?) {
        data.let {
            phrases.addAll(it as List<Phrase>)
            Log.i(TAG,"${phrases.size}")
            if(phrases.isNotEmpty()){
                fragmentView.rv_phrases.adapter?.notifyDataSetChanged()
            }else{
                showNoData(true,fragmentView.rv_phrases,fragmentView.tv_no_data)
            }
        }
    }

    override fun registerObservers() {
        mViewModel.getPhrasesByCategory(0).observe(this,this)
    }

    override fun initComponents() {
        mViewModel = ViewModelProviders.of(activity!!).get(GreetingsPhrasesViewModel::class.java)
        fragmentView.rv_phrases.layoutManager = LinearLayoutManager(context)
        fragmentView.rv_phrases.adapter = PhrasesRecyclerViewAdapter(context!!, phrases)
    }

    private lateinit var fragmentView: View
    private var phrases = mutableListOf<Phrase>()
    private lateinit var mViewModel:GreetingsPhrasesViewModel
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
        const val TAG = "GreetingsFragment"
        fun getInstance(): GreetingsFragment{
            return GreetingsFragment()
        }
    }
}