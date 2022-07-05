package com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions

import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product


fun DiaryEntry.toProduct(): Product {
    return Product(
        containerWeight = this.weight,
        nutritionValues = this.nutritionValues,
        unit = this.unit
    )
}