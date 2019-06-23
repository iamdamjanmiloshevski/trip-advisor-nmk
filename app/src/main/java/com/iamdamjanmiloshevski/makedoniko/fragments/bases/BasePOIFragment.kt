package com.iamdamjanmiloshevski.makedoniko.fragments.bases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.adapters.LandmarksRecyclerViewAdapter
import com.iamdamjanmiloshevski.makedoniko.models.Landmark

/** Created by Damjan on 23.6.2019
Project: trip-advisor-nmk
 **/
abstract class BasePOIFragment() : Fragment(),Observer<List<Landmark>>{
    fun getFragmentView(
        context: Context?,
        viewGroup: ViewGroup?,
        attachToRoot: Boolean,
        savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.tab_poi_child, viewGroup, attachToRoot)
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
        rv_data: RecyclerView, adapter: LandmarksRecyclerViewAdapter,
        no_data: AppCompatTextView, progressBar: ProgressBar,
        data: List<Landmark>?, landmarks: MutableList<Landmark>
    ) {
        data.let {
            if (it != null) {
                for (landmark in it) {
                    if (!landmarks.contains(landmark)) {
                        landmarks.add(landmark)
                    }
                }
            }
            if (landmarks.isNotEmpty()) {
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
                showNoData(false, rv_data, no_data)
            } else {
                showNoData(true, rv_data, no_data)
            }
        }
    }
}