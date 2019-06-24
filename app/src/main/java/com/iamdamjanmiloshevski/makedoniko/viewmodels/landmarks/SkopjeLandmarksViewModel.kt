package com.iamdamjanmiloshevski.makedoniko.viewmodels.landmarks

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentChange
import com.iamdamjanmiloshevski.makedoniko.adapters.LandmarksRecyclerViewAdapter
import com.iamdamjanmiloshevski.makedoniko.models.Landmark

/** Created by Damjan on 23.6.2019
Project: trip-advisor-nmk
 **/
class SkopjeLandmarksViewModel():LandmarksBaseViewModel(){
    override fun getLandmarksForCity(city: String,adapter:LandmarksRecyclerViewAdapter): LiveData<List<Landmark>> {
        mRepository.getLandmarksForCity(city).addSnapshotListener { value, e ->
            if (e != null) {
                Log.w("TAG", "Listen failed", e)
                landmarks.value = null
            }
            val landmarksList: MutableList<Landmark> = mutableListOf()
            for (doc in value!!.documentChanges) {
                val landmark = doc.document.toObject(Landmark::class.java)
                when (doc.type) {
                    DocumentChange.Type.ADDED -> {
                        if (!landmarksList.contains(landmark)) {
                            landmarksList.add(landmark)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    DocumentChange.Type.MODIFIED -> {
                        for (i in landmarksList.indices) {
                            val p = landmarksList[i]
                            if (p == landmark) {
                                landmarksList[i] = landmark
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                    DocumentChange.Type.REMOVED -> {
                        for (p in landmarksList) {
                            if (p == landmark) {
                                landmarksList.remove(landmark)
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            if (landmarksList.isNotEmpty()) {
                landmarks.value = landmarksList
            }
        }
        return landmarks
    }

}