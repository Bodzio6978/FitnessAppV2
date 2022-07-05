package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model

import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.util.NutritionType


data class Meal(
    val mealName:String,
    val diaryEntries:List<DiaryEntryWithId>
)

fun Meal.sumNutritionValues(
    nutritionType: NutritionType
):Double{
    val sum = when(nutritionType){
        is NutritionType.Calories -> {
            this.diaryEntries.sumOf {
                it.diaryEntry.nutritionValues.calories
            }
        }
        is NutritionType.Carbs -> {
            this.diaryEntries.sumOf {
                it.diaryEntry.nutritionValues.carbohydrates
            }
        }
        is NutritionType.Protein -> {
            this.diaryEntries.sumOf {
                it.diaryEntry.nutritionValues.protein
            }
        }
        is NutritionType.Fat -> {
            this.diaryEntries.sumOf {
                it.diaryEntry.nutritionValues.fat
            }
        }
    }
    return sum.toDouble()
}