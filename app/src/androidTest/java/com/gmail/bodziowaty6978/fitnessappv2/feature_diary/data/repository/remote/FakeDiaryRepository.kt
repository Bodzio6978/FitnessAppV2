package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.data.repository.remote

import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntryWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository


class FakeDiaryRepository: DiaryRepository {

    private val diaryEntries = mutableListOf<DiaryEntryWithId>()
    private val index = 0

    override suspend fun getDiaryEntries(date: String): Resource<List<DiaryEntryWithId>> {
        return Resource.Success(diaryEntries)
    }

    override suspend fun searchForProducts(productName: String): Resource<List<ProductWithId>> {
        return Resource.Success(
            data = listOf(
                ProductWithId(productName+"Id", Product(name = productName))
            )
        )
    }

    override suspend fun addDiaryEntry(diaryEntry: DiaryEntry): CustomResult {
        diaryEntries.add(DiaryEntryWithId("$index",diaryEntry))
        return CustomResult.Success
    }

    override suspend fun deleteDiaryEntry(diaryEntryId: String): CustomResult {
        diaryEntries.forEach {
            if (it.id==diaryEntryId){
                diaryEntries.remove(it)
            }
        }
        return CustomResult.Success
    }

    override suspend fun editDiaryEntry(diaryEntryWithId: DiaryEntryWithId): CustomResult {
        deleteDiaryEntry(diaryEntryId = diaryEntryWithId.id)
        diaryEntries.add(DiaryEntryWithId(diaryEntryWithId.id,diaryEntryWithId.diaryEntry))
        return CustomResult.Success
    }

    override suspend fun getLocalProductHistory(): Resource<List<ProductWithId>> {
        return Resource.Success(data = emptyList())
    }

    override suspend fun saveProductToHistory(productWithId: ProductWithId): CustomResult {
        return CustomResult.Success
    }
}