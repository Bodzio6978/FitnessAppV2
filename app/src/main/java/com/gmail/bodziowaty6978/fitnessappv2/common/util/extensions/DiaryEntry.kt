package com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Product

fun DiaryEntry.toProduct(): Product {
    return Product(
        containerWeight = this.weight,
        calories = this.calories,
        unit = this.unit
    )
}