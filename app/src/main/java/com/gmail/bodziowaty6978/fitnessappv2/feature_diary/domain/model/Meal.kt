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
                it.diaryEntry.calories
            }
        }
        is NutritionType.Carbs -> {
            this.diaryEntries.sumOf {
                it.diaryEntry.carbs
            }
        }
        is NutritionType.Protein -> {
            this.diaryEntries.sumOf {
                it.diaryEntry.protein
            }
        }
        is NutritionType.Fat -> {
            this.diaryEntries.sumOf {
                it.diaryEntry.fat
            }
        }
    }
    return sum.toDouble()
}