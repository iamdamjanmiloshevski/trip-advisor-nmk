package com.iamdamjanmiloshevski.makedoniko.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import com.iamdamjanmiloshevski.makedoniko.repositories.FirestoreRepository

/** Created by Damjan on 15.6.2019
Project: trip-advisor-nmk
 **/
abstract class BaseChildHomeViewModel : ViewModel() {
   protected var phrases: MutableLiveData<List<Phrase>> = MutableLiveData()
    protected var mRepository:FirestoreRepository = FirestoreRepository.getInstance()
    protected var eventListener:ListenerRegistration? = null
    abstract fun getPhrasesByCategory(category:Int): LiveData<List<Phrase>>
    abstract fun getPhrases(): LiveData<List<Phrase>>
    abstract fun detachListener()
}