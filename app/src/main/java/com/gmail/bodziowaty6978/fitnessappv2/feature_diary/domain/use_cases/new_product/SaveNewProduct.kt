package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.new_product

import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository

class SaveNewProduct(
    private val diaryRepository: DiaryRepository,
    private val resourceProvider: ResourceProvider
) {

    suspend operator fun invoke(
        name: String,
        containerWeight: String,
        position: Int,
        unit: String,
        calories: String,
        carbohydrates: String,
        protein: String,
        fat: String,
        barcode: String
    ): CustomResult {
        val caloriesValue = calories.toIntOrNull()
        val carbohydratesValue = carbohydrates.toDoubleOrNull()
        val proteinValue = protein.toDoubleOrNull()
        val fatValue = fat.toDoubleOrNull()
        val containerWeightValue = containerWeight.toIntOrNull()

        if (name.isBlank()) {
            return CustomResult.Error(resourceProvider.getString(R.string.please_make_product_name_is_not_empty))
        }
        if(name.length>24||name.length<4){
            return CustomResult.Error(resourceProvider.getString(R.string.please_make_sure_the_product_name_is_not_longer_than_24_characters_and_not_shorter_than_3))
        }
        if (caloriesValue==null||carbohydratesValue==null||proteinValue==null||fatValue==null||containerWeightValue==null){
            return CustomResult.Error(resourceProvider.getString(R.string.please_make_sure_all_fields_are_filled_in_correctly))
        }
        if (position != 0) {
            if (containerWeight.isBlank()) {
                return CustomResult.Error(resourceProvider.getString(R.string.please_make_sure_all_fields_are_filled_in_correctly))
            }
        }

        val product = Product(
            name = name,
            containerWeight = containerWeightValue,
            position = position,
            unit = unit,
            barcode = barcode.ifBlank { null },
            nutritionValues = NutritionValues(
                calories = caloriesValue,
                carbohydrates = carbohydratesValue,
                protein = proteinValue,
                fat = fatValue
            )
        )

        return diaryRepository.saveNewProduct(
            product = product
        )
    }
}