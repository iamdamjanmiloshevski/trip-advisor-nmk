package com.iamdamjanmiloshevski.makedoniko.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import kotlinx.android.synthetic.main.word_item.view.*

/** Created by Damjan on 11.6.2019
Project: trip-advisor-nmk
 **/
class PhraseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(phrase: Phrase){
        itemView.tv_english.text = phrase.description
        val translation = phrase.tranlation
        itemView.tv_macedonian_formal.text = translation?.formal
        itemView.tv_macedonian_informal.text = translation?.informal

    }
}