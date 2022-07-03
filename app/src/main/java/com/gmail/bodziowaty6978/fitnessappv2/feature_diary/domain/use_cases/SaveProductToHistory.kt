package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository

class SaveProductToHistory(
    private val diaryRepository: DiaryRepository
) {

    suspend operator fun invoke(
        productWithId: ProductWithId
    ):CustomResult{
        return diaryRepository.saveProductToHistory(productWithId)
    }
}