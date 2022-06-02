package com.gmail.bodziowaty6978.fitnessappv2.common.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId:String,
    @ColumnInfo(name = "username") val username:String? = null,
    @ColumnInfo(name = "nutritionValuesId") val nutritionValuesId: Int? = null,
    @ColumnInfo(name = "userInformationId") val userInformationId:Int? = null,
    @ColumnInfo(name = "areWeightDialogsEnabled") val areWeightDialogsEnabled:Boolean? = null,
)