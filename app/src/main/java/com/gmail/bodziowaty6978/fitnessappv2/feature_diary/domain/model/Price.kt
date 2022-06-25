package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Price(
    val price:Double = 0.0,
    val forWhat:String = ""
) : Parcelable

object PriceConverter{

    private val gson = Gson()

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