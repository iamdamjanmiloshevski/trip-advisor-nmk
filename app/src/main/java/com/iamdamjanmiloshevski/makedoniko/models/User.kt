package com.iamdamjanmiloshevski.makedoniko.models

/** Created by Damjan on 25.6.2019
Project: trip-advisor-nmk
 **/
data class User (var email:String="", var password:String =""){

    fun getMap(user:User):Map<String,Any>{
        val map = mutableMapOf<String,Any>()
        map["email"] = user.email
        map["password"] = user.password
        return map
    }
}