package com.iamdamjanmiloshevski.makedoniko.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.iamdamjanmiloshevski.makedoniko.models.User
import com.iamdamjanmiloshevski.makedoniko.repositories.FirestoreRepository
import timber.log.Timber

/** Created by Damjan on 28.6.2019
Project: trip-advisor-nmk
 **/
class AccountViewModel : ViewModel() {
    private val mRepository = FirestoreRepository.getInstance()
    private var userData: MutableLiveData<User> = MutableLiveData()
    private var eventListener: ListenerRegistration? = null

    fun getUser(uid: String): LiveData<User> {
        eventListener = mRepository.getUser(uid).addSnapshotListener { value, e ->
            if (e != null) {
                Timber.e(e)
                userData.value = null
            }
            value.let {
                userData.value = User(it!!.data as Map<String, Any>)
            }
        }
        return userData
    }

    fun detachListener() {
        eventListener?.remove()
    }
}