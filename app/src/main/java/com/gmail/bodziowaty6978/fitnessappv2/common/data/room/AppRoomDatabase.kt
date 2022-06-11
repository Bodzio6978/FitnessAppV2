package com.gmail.bodziowaty6978.fitnessappv2.common.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.bodziowaty6978.fitnessappv2.common.data.room.dao.ProductDao
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {

    companion object{
        const val DATABASE_NAME = "Fitness database"
    }

    abstract fun productDao(): ProductDao
}