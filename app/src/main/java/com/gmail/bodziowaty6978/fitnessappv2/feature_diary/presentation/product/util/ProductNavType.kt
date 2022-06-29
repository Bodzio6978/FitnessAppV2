package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.util

import android.os.Bundle
import androidx.navigation.NavType
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.google.gson.Gson

class ProductNavType:NavType<ProductWithId>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): ProductWithId? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ProductWithId {
        return Gson().fromJson(value, ProductWithId::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ProductWithId) {
        bundle.putParcelable(key, value)
    }
}