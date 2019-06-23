package com.iamdamjanmiloshevski.makedoniko.repositories


import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.CATEGORY
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.CITY_NAME
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.LANDMARKS_COLLECTION
import com.iamdamjanmiloshevski.makedoniko.utils.Constants.PHRASES_COLLECTION

/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class FirestoreRepository {
    private var database: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        private var instance: FirestoreRepository? = null
        fun getInstance(): FirestoreRepository {
            if (instance == null) {
                return FirestoreRepository()
            }
            return instance as FirestoreRepository
        }
    }

    fun getPhrases(): CollectionReference {
        return database.collection(PHRASES_COLLECTION)
    }

    fun getPhrasesByCategory(category: Int): Query {
        return database.collection(PHRASES_COLLECTION).whereEqualTo(CATEGORY, category)
    }

    fun getLandmarksForCity(city: String): Query {
        return database.collection(LANDMARKS_COLLECTION).whereEqualTo(CITY_NAME, city)
    }
}