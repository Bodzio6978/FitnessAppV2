package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository

class SearchForProducts(
    private val diaryRepository: DiaryRepository,
    private val resourceProvider: ResourceProvider,
    private val getDiaryHistory: GetDiaryHistory
) {

    suspend operator fun invoke(productName:String):Resource<List<ProductWithId>>{
        if (productName.isBlank()){
            return  getDiaryHistory()
        }
        return diaryRepository.searchForProducts(productName)
    }
}