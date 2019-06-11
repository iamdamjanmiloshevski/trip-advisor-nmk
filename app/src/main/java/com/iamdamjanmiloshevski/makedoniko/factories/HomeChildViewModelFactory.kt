package com.iamdamjanmiloshevski.makedoniko.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iamdamjanmiloshevski.makedoniko.viewmodels.HomeChildViewModel

/** Created by Damjan on 11.6.2019
Project: trip-advisor-nmk
 **/
class HomeChildViewModelFactory(private val category: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeChildViewModel(category) as T
    }
}