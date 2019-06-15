package com.iamdamjanmiloshevski.makedoniko.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import com.iamdamjanmiloshevski.makedoniko.repositories.FirestoreRepository

/** Created by Damjan on 15.6.2019
Project: trip-advisor-nmk
 **/
abstract class BaseChildViewModel : ViewModel() {
   protected var phrases: MutableLiveData<List<Phrase>> = MutableLiveData()
    protected var phrasesByCategory: MutableLiveData<List<Phrase>> = MutableLiveData()
    protected var mRepository:FirestoreRepository = FirestoreRepository.getInstance()

    abstract fun getPhrasesByCategory(category:Int): LiveData<List<Phrase>>
    abstract fun getPhrases(): LiveData<List<Phrase>>
}