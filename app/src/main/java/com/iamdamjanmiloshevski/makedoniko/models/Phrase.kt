package com.iamdamjanmiloshevski.makedoniko.models



/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
data class Phrase(
    var audioUrl:String = "",
    var category: Int = 0,
    var description:String = "",
    var englishTransription:String = "",
    var tranlationMacedonian: Translation? = null)