package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.util.IntroductionExpectedQuestionAnswer
import com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions.round

class CalculateNutritionValues() {


    operator fun invoke(
        gender: Int,
        currentWeight: Double,
        height: Double,
        workType: Int,
        workoutsInWeek: Int,
        activityInDay: Int,
        wantedWeight: Double,
        age: Int
    ): NutritionValues {


        val ppm = when (gender) {
            0 -> {
                (10.0 * currentWeight) + (6.25 * height) - (5.0 * age) + 5.0
            }
            else -> {
                (10.0 * currentWeight) + (6.25 * height) - (5.0 * age) - 161.0
            }
        }

        val firstFactor = when (workType) {
            0 -> 0.0
            1 -> 1.0 / 15.0
            else -> 1.0 / 15.0 * 2.0
        }

        val secondFactor = when (workoutsInWeek) {
            0 -> 0.0
            1 -> 1.0 / 30.0
            2 -> 1.0 / 30.0 * 2.0
            3 -> 1.0 / 30.0 * 3.0
            else -> 1.0 / 30.0 * 4.0
        }
        val thirdFactor = when (activityInDay) {
            0 -> 0.0
            1 -> 1.0 / 15.0
            2 -> 1.0 / 15.0 * 2.0
            else -> 1.0 / 15.0 * 3.0
        }

        val pal = 1.4 + firstFactor + secondFactor + thirdFactor

        val cpm = ppm * pal

        val wantedCalories = when {
            wantedWeight > currentWeight -> {
                cpm + 400.0
            }
            wantedWeight < currentWeight -> {
                cpm - 400.0
            }
            else -> {
                cpm + 0.0
            }
        }

        val wantedProtein = wantedCalories * 30.0 / 100.0 / 4.0
        val wantedFat = wantedCalories * 25.0 / 100.0 / 9.0
        val wantedCarbs = ((wantedCalories - ((9.0 * wantedFat) + (4.0 * wantedProtein))) / 4.0)

        return NutritionValues(
            calories = wantedCalories.toInt(),
            carbohydrates = wantedCarbs.round(2),
            protein = wantedProtein.round(2),
            fat = wantedFat.round(2)
        )
    }
}