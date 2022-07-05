package com.gmail.bodziowaty6978.fitnessappv2.common.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class NutritionValues(
    val calories:Int = 285,
    val carbohydrates:Double = 28.5,
    val protein:Double = 21.375,
    val fat:Double = 9.5
) : Parcelable