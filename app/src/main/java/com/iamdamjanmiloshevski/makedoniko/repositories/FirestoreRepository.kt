package com.iamdamjanmiloshevski.makedoniko.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.iamdamjanmiloshevski.makedoniko.models.Phrase

/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class FirestoreRepository {
    private var database: FirebaseFirestore? = null
    private var mPhrases = mutableListOf<Phrase>()

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

    fun getPhrases(category: Int): MutableLiveData<List<Phrase>> {
        val mutableData: MutableLiveData<List<Phrase>> = MutableLiveData()
        database?.collection("phrases")
            ?.addSnapshotListener(EventListener { data, e ->
                if (data != null) {
                    for (doc in data.documentChanges) {
                        val phrase = doc.document.toObject(Phrase::class.java)
                        when (doc.type) {
                            DocumentChange.Type.ADDED ->
                                if (!mPhrases.contains(phrase)) {
                                    mPhrases.add(phrase)
                                }
                            DocumentChange.Type.MODIFIED -> Log.e("modified", phrase.toString())
                            DocumentChange.Type.REMOVED -> Log.e("Removed", phrase.toString())
                        }
                    }
                    if (!mPhrases.isEmpty()) {
                        mutableData.value = mPhrases
                    }
                }
            })
        return mutableData
    }
}