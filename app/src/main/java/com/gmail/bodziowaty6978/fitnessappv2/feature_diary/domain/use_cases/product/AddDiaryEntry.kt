package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.DateModel
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository

class AddDiaryEntry(
    private val diaryRepository: DiaryRepository
) {

    suspend operator fun invoke(
        productWithId: ProductWithId,
        mealName:String,
        dateModel: DateModel,
        weight:Double,
        nutritionValues: NutritionValues,
    ):CustomResult{
        val product = productWithId.product

        val diaryEntry = DiaryEntry(
            name = product.name,
            id = productWithId.productId,
            mealName = mealName,
            date = dateModel.date,
            time = System.currentTimeMillis(),
            brand = product.brand,
            weight = weight,
            unit = product.unit.toString(),
            nutritionValues = nutritionValues
        )

        return diaryRepository.addDiaryEntry(diaryEntry)
    }
}