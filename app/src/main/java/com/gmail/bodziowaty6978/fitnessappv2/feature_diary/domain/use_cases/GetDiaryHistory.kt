package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository


class GetDiaryHistory(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke():Resource<List<ProductWithId>>{
        return repository.getLocalProductHistory()
    }
}