package com.iamdamjanmiloshevski.makedoniko.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import kotlinx.android.synthetic.main.word_item.view.*

/** Created by Damjan on 11.6.2019
Project: trip-advisor-nmk
 **/
class PhraseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(phrase: Phrase){
        with(phrase){
            itemView.tv_english.text = phrase.description
            val translation = phrase.tranlationMacedonian
            translation.let {
                itemView.tv_macedonian_informal.text = it?.informal
                itemView.tv_macedonian_formal.text = it?.formal
            }
        }
    }
}