package com.iamdamjanmiloshevski.makedoniko.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import com.iamdamjanmiloshevski.makedoniko.viewholders.PhraseViewHolder
import java.math.MathContext

/** Created by Damjan on 11.6.2019
Project: trip-advisor-nmk
 **/
class PhrasesRecyclerViewAdapter(private var mContext: Context, private var mPhrases: MutableList<Phrase>) :
    RecyclerView.Adapter<PhraseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.word_item, parent, false)
        return PhraseViewHolder(view)
    }


    override fun getItemCount(): Int {
        return mPhrases.size
    }


    override fun onBindViewHolder(holder: PhraseViewHolder, position: Int) {
        val phrase = mPhrases[position]
        phrase.let {
            holder.bind(it)
        }
    }

}