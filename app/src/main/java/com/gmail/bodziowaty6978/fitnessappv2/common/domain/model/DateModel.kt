package com.gmail.bodziowaty6978.fitnessappv2.common.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DateModel(
    val timestamp:Long = System.currentTimeMillis(),
    val date:String = "",
    val valueToDisplay:String? = null
):Parcelable