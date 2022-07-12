package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions.round
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import org.junit.Before
import org.junit.Test

internal class CreatePieChartDataTest{

    lateinit var createPieChartData: CreatePieChartData

    @Before
    fun setUp(){
        createPieChartData = CreatePieChartData()
    }

    @Test
    fun randomValues1_CorrectPieChartValues(){
        val carbohydrates = 60.0
        val protein = 60.0
        val fat = 80.0

        val product = Product(
            nutritionValues = NutritionValues(
                carbohydrates = carbohydrates,
                protein = protein,
                fat = fat
            )
        )

        val totalCalories = carbohydrates*4.0+protein*4.0+fat*9.0

        val pieChartData = createPieChartData(product = product)

        assert(pieChartData[0].value==(carbohydrates*4/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[1].value==1F)
        assert(pieChartData[2].value==(protein*4/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[3].value==1F)
        assert(pieChartData[4].value==(fat*9/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[5].value==1F)
    }

    @Test
    fun randomValues2_CorrectPieChartValues(){
        val carbohydrates = 12.0
        val protein = 435.0
        val fat = 2.69

        val product = Product(
            nutritionValues = NutritionValues(
                carbohydrates = carbohydrates,
                protein = protein,
                fat = fat
            )
        )

        val totalCalories = carbohydrates*4.0+protein*4.0+fat*9.0

        val pieChartData = createPieChartData(product = product)

        assert(pieChartData[0].value==(carbohydrates*4/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[1].value==1F)
        assert(pieChartData[2].value==(protein*4/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[3].value==1F)
        assert(pieChartData[4].value==(fat*9/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[5].value==1F)
    }

    @Test
    fun randomValues3_CorrectPieChartValues(){
        val carbohydrates = 512.7
        val protein = 78.4
        val fat = 92.6

        val product = Product(
            nutritionValues = NutritionValues(
                carbohydrates = carbohydrates,
                protein = protein,
                fat = fat
            )
        )

        val totalCalories = carbohydrates*4.0+protein*4.0+fat*9.0

        val pieChartData = createPieChartData(product = product)

        assert(pieChartData[0].value==(carbohydrates*4/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[1].value==1F)
        assert(pieChartData[2].value==(protein*4/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[3].value==1F)
        assert(pieChartData[4].value==(fat*9/totalCalories*100.0).round(1).toFloat())
        assert(pieChartData[5].value==1F)
    }
}