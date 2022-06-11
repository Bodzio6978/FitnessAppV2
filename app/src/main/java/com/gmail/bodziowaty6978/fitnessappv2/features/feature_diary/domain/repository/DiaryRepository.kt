package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntryWithId
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.ProductWithId

interface DiaryRepository {

    suspend fun getDiaryEntries(date:String): Resource<List<DiaryEntryWithId>>

    suspend fun searchForProducts(productName:String) : Resource<List<ProductWithId>>

    suspend fun getLocalProductHistory():Resource<List<Product>>

    suspend fun addDiaryEntry(diaryEntry: DiaryEntry):Result

    suspend fun deleteDiaryEntry(diaryEntryId:String):Result

    suspend fun editDiaryEntry(diaryEntryWithId: DiaryEntryWithId):Result
}