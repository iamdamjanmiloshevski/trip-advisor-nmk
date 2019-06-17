package com.iamdamjanmiloshevski.makedoniko.fragments.bases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.adapters.PhrasesRecyclerViewAdapter
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import kotlinx.android.synthetic.main.tab_home_child.*


/** Created by Damjan on 09.6.2019
Project: TripAdvisorNorthMacedonia
 **/
abstract class BaseHomeFragment : Fragment(), Observer<List<Phrase>> {

    fun getFragmentView(
        context: Context?,
        viewGroup: ViewGroup?,
        attachToRoot: Boolean,
        savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context)
            .inflate(com.iamdamjanmiloshevski.makedoniko.R.layout.tab_home_child, viewGroup, attachToRoot)
    }

    abstract fun registerObservers()
    abstract fun removeObservers()
    abstract fun initComponents()
    fun showNoData(show: Boolean, rv_data: RecyclerView, tv_msg: AppCompatTextView) {
        if (show) {
            rv_data.visibility = View.GONE
            tv_msg.visibility = View.VISIBLE
        } else {
            rv_data.visibility = View.VISIBLE
            tv_msg.visibility = View.GONE
        }
    }

    fun getData(
        rv_data: RecyclerView, adapter: PhrasesRecyclerViewAdapter,
        no_data: AppCompatTextView, progressBar: ProgressBar,
        data: List<Phrase>?, phrases: MutableList<Phrase>
    ) {
        data.let {
            if (it != null) {
                for (phrase in it) {
                    if (!phrases.contains(phrase)) {
                        phrases.add(phrase)
                    }
                }
            }
            if (phrases.isNotEmpty()) {
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
                showNoData(false, rv_data, no_data)
            } else {
                showNoData(true, rv_data, no_data)
            }
        }
    }
}