package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import org.junit.Before
import org.junit.Test

internal class CalculateNutritionValuesTest{

    lateinit var calculateNutritionValues: CalculateNutritionValues

    @Before
    fun setUp(){
        calculateNutritionValues = CalculateNutritionValues()
    }

    @Test
    fun randomValues1_CorrectNewValues(){
        val newValues = calculateNutritionValues(
            weight = 100.0,
            product = Product(
                nutritionValues = NutritionValues(
                    calories = 100,
                    protein = 100.0,
                    carbohydrates = 100.0,
                    fat = 100.0
                )
            )
        )
        assert(newValues.calories==100)
        assert(newValues.carbohydrates==100.0)
        assert(newValues.protein==100.0)
        assert(newValues.fat==100.0)
    }

    @Test
    fun randomValues2_CorrectNewValues(){
        val newValues = calculateNutritionValues(
            weight = 170.0,
            product = Product(
                nutritionValues = NutritionValues(
                    calories = 19,
                    protein = 0.9,
                    carbohydrates = 2.9,
                    fat = 0.2
                )
            )
        )
        assert(newValues.calories==32)
        assert(newValues.carbohydrates==4.9)
        assert(newValues.protein==1.5)
        assert(newValues.fat==0.3)
    }

    @Test
    fun randomValues3_CorrectNewValues(){
        val newValues = calculateNutritionValues(
            weight = 60.0,
            product = Product(
                nutritionValues = NutritionValues(
                    calories = 92,
                    protein = 22.0,
                    carbohydrates = 1.0,
                    fat = 0.0
                )
            )
        )
        assert(newValues.calories==55)
        assert(newValues.carbohydrates==0.6)
        assert(newValues.protein==13.2)
        assert(newValues.fat==0.0)
    }
}