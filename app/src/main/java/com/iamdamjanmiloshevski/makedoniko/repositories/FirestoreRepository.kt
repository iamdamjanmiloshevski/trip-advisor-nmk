package com.iamdamjanmiloshevski.makedoniko.repositories

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import com.iamdamjanmiloshevski.makedoniko.models.Translation

/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class FirestoreRepository {
    private var database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var phrases = mutableListOf<Phrase>()
    companion object {
        private var instance: FirestoreRepository? = null
        fun getInstance(): FirestoreRepository {
            if (instance == null) {
                return FirestoreRepository()
            }
            return instance as FirestoreRepository
        }
    }
    fun getPhrases():CollectionReference{
        return database.collection("phrases")
    }
    fun getDummyData():MutableLiveData<List<Phrase>>{
        val translation = Translation("formal","informal","transliteration")
        val phrase = Phrase("",0,"test description","test transcription",translation)
        phrases.add(phrase)
        val data:MutableLiveData<List<Phrase>> = MutableLiveData()
        data.value = phrases

        return data
    }
}