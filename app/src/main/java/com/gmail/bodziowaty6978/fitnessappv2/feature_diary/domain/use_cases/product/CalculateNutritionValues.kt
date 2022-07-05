package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product

class CalculateNutritionValues{

    operator fun invoke(weight: Double, product: Product): NutritionValues {
        val nutritionValues = product.nutritionValues

        val newCalories = nutritionValues.calories/100*weight
        val newCarbohydrates = nutritionValues.carbohydrates/100.0*weight
        val newProtein = nutritionValues.protein/100.0*weight
        val newFat = nutritionValues.fat/100.0*weight

        return NutritionValues(
            calories = newCalories.toInt(),
            carbohydrates = newCarbohydrates,
            protein = newProtein,
            fat = newFat
        )
    }
}