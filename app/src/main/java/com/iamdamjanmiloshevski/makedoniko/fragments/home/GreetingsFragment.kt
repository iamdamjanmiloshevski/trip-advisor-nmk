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
import com.iamdamjanmiloshevski.makedoniko.viewmodels.home.GreetingsPhrasesHomeViewModel
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
       fragmentView.progressbar.visibility=View.VISIBLE
        getData(
            fragmentView.rv_phrases,
            fragmentView.rv_phrases.adapter as PhrasesRecyclerViewAdapter,
            fragmentView.tv_no_data,
            fragmentView.progressbar,
            data,
            phrases
        )
    }

    override fun registerObservers() {
        mViewModel.getPhrasesByCategory(0).observe(this,this)
    }

    override fun initComponents() {
        mViewModel = ViewModelProviders.of(activity!!).get(GreetingsPhrasesHomeViewModel::class.java)
        fragmentView.rv_phrases.layoutManager = LinearLayoutManager(context)
        fragmentView.rv_phrases.adapter = PhrasesRecyclerViewAdapter(context!!, phrases)
        fragmentView.progressbar.visibility = View.VISIBLE
        if(phrases.isEmpty()){
            fragmentView.progressbar.visibility=View.GONE
            showNoData(true, fragmentView.rv_phrases, fragmentView.tv_no_data)
        }
    }

    private lateinit var fragmentView: View
    private var phrases = mutableListOf<Phrase>()
    private lateinit var mViewModel: GreetingsPhrasesHomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = getFragmentView(context, container, false, savedInstanceState)
        initComponents()
        registerObservers()
        return fragmentView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
        mViewModel.detachListener()
    }

    companion object {
        const val TAG = "GreetingsFragment"
        fun getInstance(): GreetingsFragment{
            return GreetingsFragment()
        }
    }
}