package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model

import android.os.Parcelable
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class DiaryEntry(
    val name: String = "",
    val id: String = "",
    val mealName: String = "",
    val date: String = Date(System.currentTimeMillis()).toString(),
    val time:Long = System.currentTimeMillis(),
    val brand: String? = "",
    val weight: Double = 0.0,
    val unit: String = "",
    val nutritionValues: NutritionValues = NutritionValues()):Parcelable