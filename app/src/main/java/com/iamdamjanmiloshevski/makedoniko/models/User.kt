package com.iamdamjanmiloshevski.makedoniko.models


/** Created by Damjan on 25.6.2019
Project: trip-advisor-nmk
 **/
data class User(var uid: String = "", var email: String = "", var displayName: String = "", var photoUrl: String = "") {
    constructor(data: Map<String, Any>) : this() {
        uid = if (data.containsKey("uid") && data["uid"] != null) {
            data["uid"] as String
        } else {
            ""
        }
        email = if (data.containsKey("email") && data["email"] != null) {
            data["email"] as String
        } else {
            ""
        }
        displayName = if (data.containsKey("displayName") && data["displayName"] != null) {
            data["displayName"] as String
        } else {
            ""
        }
        photoUrl = if (data.containsKey("photoUrl") && data["photoUrl"] != null) {
            data["photoUrl"] as String
        } else {
            ""
        }
    }

    fun getMap(user: User): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["uid"] = user.uid
        map["email"] = user.email
        map["displayName"] = user.displayName
        map["photoUrl"] = user.photoUrl
        return map
    }
}