package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.model

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.util.IntroductionExpectedQuestionAnswer

data class Question(
    val title:String,
    val unit:String = "",
    val tiles:List<String> = emptyList()
)
