package com.gmail.bodziowaty6978.fitnessappv2.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NutritionValues(
    val calories:Int = 0,
    val carbohydrates:Double = 0.0,
    val protein:Double = 0.0,
    val fat:Double = 0.0
)