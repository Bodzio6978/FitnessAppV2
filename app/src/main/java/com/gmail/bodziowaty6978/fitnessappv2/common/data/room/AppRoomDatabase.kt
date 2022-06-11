package com.gmail.bodziowaty6978.fitnessappv2.common.data.room

import androidx.room.Database
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Product

@Database(entities = [Product::class], version = 1)
class RoomDatabase {
    abstract fun productDao(): ProductDao()
}