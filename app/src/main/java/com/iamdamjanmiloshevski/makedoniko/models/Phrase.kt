package com.iamdamjanmiloshevski.makedoniko.models


/** Created by Damjan on 10.6.2019
Project: trip-advisor-nmk
 **/
data class Phrase(
    var audio: Audio?=null,
    var category: Int = 0,
    var description: String = "",
    var englishTranslation:String="",
    var englishTranscriptionInformal: String = "",
    var englishTranscriptionFormal: String="",
    var translationMacedonian: Translation?=null
)