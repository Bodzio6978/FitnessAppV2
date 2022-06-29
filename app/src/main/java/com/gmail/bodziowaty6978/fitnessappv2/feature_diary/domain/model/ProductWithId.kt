package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ProductWithId(
    @PrimaryKey val productId:String,
    val product: Product
) : Parcelable
