package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import android.util.Log
import com.github.mikephil.charting.data.PieEntry
import com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions.round
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG

class CreatePieChartData {


    operator fun invoke(product: Product): List<PieEntry>{
        val nutritionValues = product.nutritionValues

        val caloriesFromCarbohydrates = nutritionValues.carbohydrates * 4.0
        val caloriesFromProtein = nutritionValues.protein * 4.0
        val caloriesFromFat = nutritionValues.fat * 9.0

        val sum = caloriesFromCarbohydrates + caloriesFromProtein + caloriesFromFat

        val carbohydratesValue = (((caloriesFromCarbohydrates / sum * 100.0)).round(2)).toFloat()
        val proteinValue = (((caloriesFromProtein / sum * 100.0)).round(2)).toFloat()
        val fatValue = (((caloriesFromFat / sum * 100.0)).round(2)).toFloat()

        Log.e(TAG,"$carbohydratesValue $proteinValue, $fatValue")

        return listOf(
            PieEntry(carbohydratesValue, "Carbohydrates"),
            PieEntry(1F, ""),
            PieEntry(proteinValue, "Protein"),
            PieEntry(1F, ""),
            PieEntry(fatValue, "Fat"),
            PieEntry(1F, "")
        )
    }
}