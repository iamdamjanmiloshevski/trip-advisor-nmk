package com.iamdamjanmiloshevski.makedoniko.viewmodels.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.iamdamjanmiloshevski.makedoniko.models.Phrase

/** Created by Damjan on 15.6.2019
Project: trip-advisor-nmk
 **/
/**
 * ViewModel used to display all phrases in AllPhrasesFragment
 */
class AllPhrasesHomeViewModel : BaseChildHomeViewModel() {
    override fun detachListener() {
       eventListener?.remove()
    }

    /**
     * not used
     */
    override fun getPhrasesByCategory(category: Int): LiveData<List<Phrase>> {
        return MutableLiveData()
    }

    override fun getPhrases(): LiveData<List<Phrase>> {
        eventListener = mRepository.getPhrases().addSnapshotListener { value, e ->
            if (e != null) {
                Log.w("TAG", "Listen failed", e)
                phrases.value = null
            }
            val phrasesList: MutableList<Phrase> = mutableListOf()
            value.let {
                for (doc in it!!.documentChanges) {
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

        }
        return phrases
    }
}