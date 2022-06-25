package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class DiaryEntry(
    val name: String = "",
    val id: String = "",
    val mealName: String = "",
    val date: String = Date(System.currentTimeMillis()).toString(),
    val time:Long = System.currentTimeMillis(),
    val brand: String = "",
    val weight: Double = 0.0,
    val unit: String = "",
    val calories: Int = 0,
    val carbs: Double = 0.0,
    val protein: Double = 0.0,
    val fat: Double = 0.0):Parcelable