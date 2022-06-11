package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository.DiaryRepository

class SearchForProducts(
    private val diaryRepository: DiaryRepository
) {

    suspend operator fun invoke(productName:String):Resource<List<ProductWithId>>{
        return diaryRepository.searchForProducts(productName)
    }
}