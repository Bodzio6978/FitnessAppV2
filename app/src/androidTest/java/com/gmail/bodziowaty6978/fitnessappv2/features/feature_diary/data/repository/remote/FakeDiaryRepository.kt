package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.data.repository.remote

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntryWithId
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository.DiaryRepository


class FakeDiaryRepository: DiaryRepository {

    private val diaryEntries = mutableListOf<DiaryEntryWithId>()
    private val index = 0

    override suspend fun getDiaryEntries(date: String): Resource<List<DiaryEntryWithId>> {
        return Resource.Success(diaryEntries)
    }

    override suspend fun searchForProducts(productName: String): Resource<List<ProductWithId>> {
        TODO("Not yet implemented")
    }

    override suspend fun addDiaryEntry(diaryEntry: DiaryEntry): Result {
        diaryEntries.add(DiaryEntryWithId("$index",diaryEntry))
        return Result.Success
    }

    override suspend fun deleteDiaryEntry(diaryEntryId: String): Result {
        diaryEntries.forEach {
            if (it.id==diaryEntryId){
                diaryEntries.remove(it)
            }
        }
        return Result.Success
    }

    override suspend fun editDiaryEntry(diaryEntryWithId: DiaryEntryWithId): Result {
        deleteDiaryEntry(diaryEntryId = diaryEntryWithId.id)
        diaryEntries.add(DiaryEntryWithId(diaryEntryWithId.id,diaryEntryWithId.diaryEntry))
        return Result.Success
    }
}