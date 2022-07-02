package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository

class SearchForProducts(
    private val diaryRepository: DiaryRepository,
    private val resourceProvider: ResourceProvider
) {

    suspend operator fun invoke(productName:String):Resource<List<ProductWithId>>{
        if (productName.isBlank()){
            return Resource.Error(uiText = resourceProvider.getString(R.string.please_make_sure_you_have_entered_some_text_before_searching))
        }
        return diaryRepository.searchForProducts(productName)
    }
}