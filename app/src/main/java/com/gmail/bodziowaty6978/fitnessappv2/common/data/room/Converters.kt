package com.gmail.bodziowaty6978.fitnessappv2.common.data.room

import androidx.room.TypeConverter
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Price
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun productToString(product: Product):String{
        return gson.toJson(product) ?: ""
    }

    @TypeConverter
    @JvmStatic
    fun stringToProduct(data: String?): Product {
        return data?.let {
            gson.fromJson(data, Product::class.java)
        } ?: Product()
    }

    @TypeConverter
    @JvmStatic
    fun priceToString(listOfPrices: List<Price>):String{
        return gson.toJson(listOfPrices) ?: ""
    }

    @TypeConverter
    @JvmStatic
    fun stringToPrice(data: String?):List<Price>{
        return data?.let {
            val listType = object : TypeToken<List<Price>>() {}.type
            gson.fromJson<List<Price>>(data, listType)
        } ?: emptyList<Price>()
    }
}