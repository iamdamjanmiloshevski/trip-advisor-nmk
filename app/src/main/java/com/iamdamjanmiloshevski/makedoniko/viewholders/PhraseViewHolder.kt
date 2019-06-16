package com.iamdamjanmiloshevski.makedoniko.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import kotlinx.android.synthetic.main.word_item.view.*

/** Created by Damjan on 11.6.2019
Project: trip-advisor-nmk
 **/
class PhraseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(phrase: Phrase) {
        with(phrase) {
            val audio = this.audio
            val translationMacedonian = this.translationMacedonian
            itemView.tv_english.text = this.englishTranslation
            itemView.tv_description.text = this.description
            val transcriptionInformal = this.englishTranscriptionInformal + "(inf.)"
            itemView.tv_transcription_informal.text = transcriptionInformal
            if(this.englishTranscriptionFormal != ""){
                val transcriptionFormal = this.englishTranscriptionFormal + "(frm.)"
                itemView.tv_transcription_formal.text = transcriptionFormal
                itemView.tv_transcription_formal.visibility = View.VISIBLE
            }else{
                itemView.tv_transcription_formal.visibility = View.GONE
            }
            itemView.tv_translation_inf.text = translationMacedonian?.informal
            if(translationMacedonian?.formal != ""){
                itemView.lyt_translation_formal.visibility = View.VISIBLE
                itemView.tv_translation_frm.text = translationMacedonian?.formal
            }else{
                itemView.lyt_translation_formal.visibility = View.GONE
            }
        }

    }
}