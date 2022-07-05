package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components

import com.github.mikephil.charting.data.PieEntry
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues

data class NutritionData(
    val nutritionValues: NutritionValues = NutritionValues(),
    val pieEntries:List<PieEntry> = emptyList()
)

