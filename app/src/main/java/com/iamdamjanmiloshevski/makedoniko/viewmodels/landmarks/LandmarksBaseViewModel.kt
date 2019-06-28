package com.iamdamjanmiloshevski.makedoniko.viewmodels.landmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.iamdamjanmiloshevski.makedoniko.adapters.LandmarksRecyclerViewAdapter
import com.iamdamjanmiloshevski.makedoniko.models.Landmark
import com.iamdamjanmiloshevski.makedoniko.repositories.FirestoreRepository

/** Created by Damjan on 23.6.2019
Project: trip-advisor-nmk
 **/
abstract class LandmarksBaseViewModel() : ViewModel() {
    protected var landmarks: MutableLiveData<List<Landmark>> = MutableLiveData()
    protected var mRepository: FirestoreRepository = FirestoreRepository.getInstance()

    abstract fun getLandmarksForCity(city: String, adapter: LandmarksRecyclerViewAdapter): LiveData<List<Landmark>>
}
