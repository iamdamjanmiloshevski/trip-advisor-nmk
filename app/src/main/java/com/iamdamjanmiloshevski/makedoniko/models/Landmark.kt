package com.iamdamjanmiloshevski.makedoniko.models

import com.google.firebase.firestore.GeoPoint

/** Created by Damjan on 23.6.2019
Project: trip-advisor-nmk
 **/
data class Landmark(
    var landmarkName: String = "",
    var landmarkDescription: String = "",
    var imageUrl: String = "",
    var coordinates: GeoPoint? = null,
    var cityName: String = "",
    var fullDescription: String = "",
    var source: String = ""
)