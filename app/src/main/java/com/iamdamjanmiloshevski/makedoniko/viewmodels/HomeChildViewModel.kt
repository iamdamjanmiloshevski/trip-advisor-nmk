package com.iamdamjanmiloshevski.makedoniko.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iamdamjanmiloshevski.makedoniko.enums.PhraseCategory
import com.iamdamjanmiloshevski.makedoniko.models.Phrase
import com.iamdamjanmiloshevski.makedoniko.repositories.FirestoreRepository


/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class HomeChildViewModel(category:Int) : ViewModel() {
    private var mPhrases: MutableLiveData<List<Phrase>>? = null
    private var mRepository: FirestoreRepository? = null

    init {
        mRepository = FirestoreRepository.getInstance()
        mPhrases = mRepository?.getPhrases(category)
    }

    fun getPhrases(): LiveData<List<Phrase>>? {
        return mPhrases
    }
}