package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntryWithId
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result

interface DiaryRepository {

    suspend fun getDiaryEntries(date:String): Resource<List<DiaryEntryWithId>>

    suspend fun addDiaryEntry(diaryEntry: DiaryEntry):Result

    suspend fun deleteDiaryEntry(diaryEntryId:String):Result

    suspend fun editDiaryEntry(diaryEntryWithId: DiaryEntryWithId):Result
}