package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.DateModel
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository

class AddDiaryEntry(
    private val diaryRepository: DiaryRepository,
    private val resourceProvider: ResourceProvider
) {

    suspend operator fun invoke(
        productWithId: ProductWithId,
        mealName: String,
        dateModel: DateModel,
        weight: Int?,
        nutritionValues: NutritionValues,
    ): CustomResult {
        return weight?.let {
            if (weight==0){
                CustomResult.Error(
                    message = resourceProvider.getString(R.string.incorrect_weight_was_entered)
                )
            }else{
                val product = productWithId.product

                val diaryEntry = DiaryEntry(
                    name = product.name,
                    id = productWithId.productId,
                    mealName = mealName,
                    date = dateModel.date,
                    time = System.currentTimeMillis(),
                    weight = it,
                    unit = product.unit,
                    nutritionValues = nutritionValues
                )

                diaryRepository.addDiaryEntry(diaryEntry)
            }
        } ?: CustomResult.Error(
            message = resourceProvider.getString(R.string.incorrect_weight_was_entered)
        )


    }
}