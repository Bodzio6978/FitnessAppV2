package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.new_product

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues

class CalculateNutritionValuesIn100G {

    operator fun invoke(
        calories:Int,
        carbohydrates:Double,
        protein:Double,
        fat:Double,
        weight:Int
    ):NutritionValues{
        return NutritionValues(
            calories = (calories.toDouble()/weight*100.0).toInt(),
            carbohydrates = (carbohydrates/weight*100.0),
            protein = (protein/weight*100.0),
            fat = (fat/weight*100.0),
        )
    }
}