package com.iamdamjanmiloshevski.makedoniko.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.models.Landmark
import com.iamdamjanmiloshevski.makedoniko.viewholders.LandmarkViewHolder

/** Created by Damjan on 23.6.2019
Project: trip-advisor-nmk
 **/
class LandmarksRecyclerViewAdapter(private var mContext: Context, private var mLandmarks: List<Landmark>) :
    RecyclerView.Adapter<LandmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.landmark_item, parent, false)
        return LandmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: LandmarkViewHolder, position: Int) {
        val landmark = mLandmarks[position]
        holder.bind(landmark)
    }

    override fun getItemCount(): Int {
        return mLandmarks.size
    }
}