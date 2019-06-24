package com.iamdamjanmiloshevski.makedoniko.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.models.Landmark
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.landmark_item.view.*

/** Created by Damjan on 23.6.2019
Project: trip-advisor-nmk
 **/
class LandmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(landmark: Landmark) {
        with(landmark) {
            val imageUrl = this.imageUrl
            Picasso.with(itemView.context)
                .load(imageUrl)
                .error(R.drawable.image_placeholder)
                .into(itemView.iv_landmark_image)
            itemView.tv_landmark_name.text = this.shortName
            itemView.tv_landmark_text.text = this.landmarkDescription
        }
    }
}