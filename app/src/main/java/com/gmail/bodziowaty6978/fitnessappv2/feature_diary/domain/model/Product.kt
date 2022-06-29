package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @ColumnInfo(name = "author") val author: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "brand") val brand: String? = null,
    @ColumnInfo(name = "containerWeight") val containerWeight: Double = 0.0,
    @ColumnInfo(name = "position") val position: Int = 0,
    @ColumnInfo(name = "unit") val unit: String? = "",
    @ColumnInfo(name = "calories") val calories: Int = 0,
    @ColumnInfo(name = "carbs") val carbs: Double = 0.0,
    @ColumnInfo(name = "protein") val protein: Double = 0.0,
    @ColumnInfo(name = "fat") val fat: Double = 0.0,
    @ColumnInfo(name = "barcode") val barcode: String? = "",
    @ColumnInfo(name = "prices") var prices: List<Price> = emptyList()
) : Parcelable




