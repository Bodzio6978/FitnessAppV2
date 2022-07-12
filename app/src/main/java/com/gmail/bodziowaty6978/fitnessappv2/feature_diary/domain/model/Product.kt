package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @ColumnInfo(name = "author") val author: String = "Joe Mama",
    @ColumnInfo(name = "name") val name: String = "White rice",
    @ColumnInfo(name = "brand") val brand: String? = null,
    @ColumnInfo(name = "containerWeight") val containerWeight: Int = 0,
    @ColumnInfo(name = "position") val position: Int = 0,
    @ColumnInfo(name = "unit") val unit: String = "g",
    @ColumnInfo(name = "nutritionValues") val nutritionValues: NutritionValues = NutritionValues(),
    @ColumnInfo(name = "barcode") val barcode: String? = "",
    @ColumnInfo(name = "prices") var prices: List<Price> = emptyList()
) : Parcelable




