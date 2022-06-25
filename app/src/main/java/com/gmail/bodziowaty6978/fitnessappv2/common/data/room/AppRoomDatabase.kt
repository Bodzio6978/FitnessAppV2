package com.gmail.bodziowaty6978.fitnessappv2.common.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.bodziowaty6978.fitnessappv2.common.data.room.dao.ProductDao
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.PriceConverter
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(PriceConverter::class)
abstract class AppRoomDatabase: RoomDatabase() {

    companion object{
        const val DATABASE_NAME = "Fitness database"
    }

    abstract fun productDao(): ProductDao
}