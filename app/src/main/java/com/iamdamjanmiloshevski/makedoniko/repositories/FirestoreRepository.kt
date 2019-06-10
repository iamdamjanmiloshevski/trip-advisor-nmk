package com.iamdamjanmiloshevski.makedoniko.repositories

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.iamdamjanmiloshevski.makedoniko.models.Phrase

/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class FirestoreRepository {
    private var database: FirebaseFirestore? = null

    init {
        database = FirebaseFirestore.getInstance()
    }

    companion object {
        private var instance: FirestoreRepository? = null
        fun getInstance(): FirestoreRepository {
            if (instance == null) {
                return FirestoreRepository()
            }
            return instance as FirestoreRepository
        }
    }

    fun getPhrases(category: Int): MutableLiveData<MutableList<Phrase>> {
        val data: MutableLiveData<MutableList<Phrase>> = MutableLiveData()
        database?.collection("phrases")
            ?.addSnapshotListener(EventListener { data, e ->

        })
        return data
    }
}