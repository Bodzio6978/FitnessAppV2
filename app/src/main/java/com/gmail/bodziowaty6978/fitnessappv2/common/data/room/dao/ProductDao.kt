package com.gmail.bodziowaty6978.fitnessappv2.common.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId

@Dao
interface ProductDao {

    @Query("SELECT * FROM productwithid")
    suspend fun getHistory():List<ProductWithId>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProductToHistory(productWithId: ProductWithId)
}