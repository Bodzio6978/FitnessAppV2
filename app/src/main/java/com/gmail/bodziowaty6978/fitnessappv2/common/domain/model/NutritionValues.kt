package com.gmail.bodziowaty6978.fitnessappv2.common.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class NutritionValues(
    val calories:Int = 285,
    val carbohydrates:Double = 100.0,
    val protein:Double = 50.0,
    val fat:Double = 10.0
) : Parcelable