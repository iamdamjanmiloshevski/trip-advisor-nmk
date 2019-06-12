package com.iamdamjanmiloshevski.makedoniko.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import com.iamdamjanmiloshevski.makedoniko.repositories.FirestoreRepository


/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class HomeChildViewModel : ViewModel() {
    private var phrases: MutableLiveData<List<Phrase>> = MutableLiveData()
    private var mRepository: FirestoreRepository = FirestoreRepository.getInstance()

    init {
        phrases = mRepository.getDummyData()
    }

    fun getName(): String {
        return "HomeChildViewModel"
    }
     fun getPhrases():LiveData<List<Phrase>>{
        return phrases
    }
//    fun getPhrasesByCategory(category: Int): LiveData<List<Phrase>>? {
//
//    }
//    fun getPhrases(): LiveData<List<Phrase>> {
//        mRepository.getPhrases().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
//            if (e != null) {
//                Log.w("ViewModel", "Listen failed.", e)
//                phrases.value = null
//                return@EventListener
//            }
//
//            var phrasesList : MutableList<Phrase> = mutableListOf()
//            for (doc in value!!) {
//                var addressItem = doc.toObject(Phrase::class.java)
//                phrasesList.add(addressItem)
//            }
//            phrases.value = phrasesList
//        })
//        return phrases
//    }
}