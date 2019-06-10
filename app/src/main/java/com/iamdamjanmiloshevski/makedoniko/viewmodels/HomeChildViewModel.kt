package com.iamdamjanmiloshevski.makedoniko.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iamdamjanmiloshevski.makedoniko.models.Phrase


/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
class HomeChildViewModel: ViewModel() {
    private var mPhrases: MutableLiveData<MutableList<Phrase>>? = null
}