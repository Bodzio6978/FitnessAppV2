package com.gmail.bodziowaty6978.fitnessappv2.common.data.room.dao

import androidx.room.Query
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Product

interface ProductDao {

    @Query("SELECT * FROM product")
    suspend fun getHistory():List<Product>
}