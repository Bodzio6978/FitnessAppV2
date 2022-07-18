package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.new_product

import org.junit.Before
import org.junit.Test

internal class CalculateNutritionValuesIn100GTest {

    lateinit var calculateNutritionValuesIn100G: CalculateNutritionValuesIn100G

    @Before
    fun setUp() {
        calculateNutritionValuesIn100G = CalculateNutritionValuesIn100G()
    }

    @Test
    fun randomValues1_CorrectNewValues() {
        val weight = 100
        val calories = 100
        val protein = 100.0
        val carbohydrates = 100.0
        val fat = 100.0
        val newValues = calculateNutritionValuesIn100G(
            weight = weight,
            calories = calories,
            protein = protein,
            carbohydrates = carbohydrates,
            fat = fat
        )
        assert(newValues.calories == (calories.toDouble()/weight*100.0).toInt())
        assert(newValues.carbohydrates == carbohydrates/weight*100.0)
        assert(newValues.protein == protein/weight*100.0)
        assert(newValues.fat == fat/weight*100.0)
    }

    @Test
    fun randomValues2_CorrectNewValues() {
        val weight = 170
        val calories = 19
        val protein = 0.9
        val carbohydrates = 2.9
        val fat = 0.2
        val newValues = calculateNutritionValuesIn100G(
            weight = weight,
            calories = calories,
            protein = protein,
            carbohydrates = carbohydrates,
            fat = fat
        )
        assert(newValues.calories == (calories.toDouble()/weight*100.0).toInt())
        assert(newValues.carbohydrates == carbohydrates/weight*100.0)
        assert(newValues.protein == protein/weight*100.0)
        assert(newValues.fat == fat/weight*100.0)
    }

    @Test
    fun randomValues3_CorrectNewValues() {
        val weight = 60
        val calories = 92
        val protein = 22.0
        val carbohydrates = 1.0
        val fat = 0.0
        val newValues = calculateNutritionValuesIn100G(
            weight = weight,
            calories = calories,
            protein = protein,
            carbohydrates = carbohydrates,
            fat = fat
        )
        assert(newValues.calories == (calories.toDouble()/weight*100.0).toInt())
        assert(newValues.carbohydrates == carbohydrates/weight*100.0)
        assert(newValues.protein == protein/weight*100.0)
        assert(newValues.fat == fat/weight*100.0)
    }
}