package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import android.util.Log
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions.round
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG

class CalculateNutritionValues{

    operator fun invoke(weight: Double, product: Product): NutritionValues {
        val nutritionValues = product.nutritionValues

        val newCalories = (nutritionValues.calories.toDouble()/100.0*weight).toInt()
        val newCarbohydrates = (nutritionValues.carbohydrates/100.0*weight).round(2)
        val newProtein = (nutritionValues.protein/100.0*weight).round(2)
        val newFat = (nutritionValues.fat/100.0*weight).round(2)

        val newValues = NutritionValues(
            calories = newCalories,
            carbohydrates = newCarbohydrates,
            protein = newProtein,
            fat = newFat
        )

        Log.e(TAG,newValues.toString())

        return newValues
    }
}