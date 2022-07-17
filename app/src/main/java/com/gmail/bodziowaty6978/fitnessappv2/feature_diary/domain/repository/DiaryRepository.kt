package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository

import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntryWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId

interface DiaryRepository {

    suspend fun getDiaryEntries(date:String): Resource<List<DiaryEntryWithId>>

    suspend fun searchForProducts(productName:String) : Resource<List<ProductWithId>>

    suspend fun getLocalProductHistory():Resource<List<ProductWithId>>

    suspend fun saveProductToHistory(productWithId: ProductWithId):CustomResult

    suspend fun addDiaryEntry(diaryEntry: DiaryEntry):CustomResult

    suspend fun deleteDiaryEntry(diaryEntryId:String):CustomResult

    suspend fun editDiaryEntry(diaryEntryWithId: DiaryEntryWithId):CustomResult

    suspend fun saveNewProduct(product: Product):CustomResult
}