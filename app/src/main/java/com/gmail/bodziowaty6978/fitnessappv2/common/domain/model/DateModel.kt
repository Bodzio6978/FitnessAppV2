package com.gmail.bodziowaty6978.fitnessappv2.common.domain.model

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.util.DateType

data class DateModel(
    val timestamp:Long,
    val date:String,
    val dateType:DateType = DateType.Random
)