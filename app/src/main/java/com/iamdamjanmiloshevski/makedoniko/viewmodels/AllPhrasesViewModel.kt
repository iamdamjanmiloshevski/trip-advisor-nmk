package com.iamdamjanmiloshevski.makedoniko.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentChange
import com.iamdamjanmiloshevski.makedoniko.models.Phrase

/** Created by Damjan on 15.6.2019
Project: trip-advisor-nmk
 **/
/**
 * ViewModel used to display all phrases in AllPhrasesFragment
 */
class AllPhrasesViewModel : BaseChildViewModel(){

    /**
     * not used
     */
    override fun getPhrasesByCategory(category:Int): LiveData<List<Phrase>> {
        return MutableLiveData<List<Phrase>>()
    }

    override fun getPhrases(): LiveData<List<Phrase>> {
        mRepository.getPhrases().addSnapshotListener { value, e ->
            if (e != null) {
                Log.w("TAG", "Listen failed", e)
                phrases.value = null
            }
            val phrasesList: MutableList<Phrase> = mutableListOf()
            for (doc in value!!.documentChanges) {
                val phrase = doc.document.toObject(Phrase::class.java)
                when (doc.type) {
                    DocumentChange.Type.ADDED ->
                        if (!phrasesList.contains(phrase)) {
                            phrasesList.add(phrase)
                        }
                    DocumentChange.Type.MODIFIED -> {
                        for (i in phrasesList.indices) {
                            val p = phrasesList[i]
                            if (p == phrase) {
                                phrasesList[i] = phrase
                            }
                        }
                    }
                    DocumentChange.Type.REMOVED -> {
                        for (p in phrasesList) {
                            if (p == phrase) {
                                phrasesList.remove(phrase)
                            }
                        }
                    }
                }
            }
            if (phrasesList.isNotEmpty()) {
                phrases.value = phrasesList
            }
        }
        return phrases
    }

}