package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.util

sealed class NutritionType(){
    object Calories:NutritionType()
    object Carbs:NutritionType()
    object Protein:NutritionType()
    object Fat:NutritionType()
}
