package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Meal
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDiaryEntries(
    private val diaryRepository: DiaryRepository,
    private val sortDiaryEntries: SortDiaryEntries,
) {

    suspend operator fun invoke(
        date: String,
        mealNames: List<String>
    ): Resource<List<Meal>> {
        return when (val response = diaryRepository.getDiaryEntries(date = date)) {
            is Resource.Success -> {
                withContext(Dispatchers.Default) {
                    val data = response.data!!
                    val sortedEntries = sortDiaryEntries(data,mealNames)

                    Resource.Success(sortedEntries)
                }
            }
            is Resource.Error -> {
                Resource.Error(response.uiText!!)
            }
        }
    }
}