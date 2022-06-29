package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetDiaryHistoryTest{

    @Mock
    private lateinit var mockDiaryRepository: DiaryRepository

    private lateinit var getDiaryHistory: GetDiaryHistory

    @Before
    fun setUp() = runTest{
        mockDiaryRepository = Mockito.mock(DiaryRepository::class.java)
        Mockito.`when`(mockDiaryRepository.getLocalProductHistory()).thenReturn(
            Resource.Success(data = emptyList())
        )
        getDiaryHistory = GetDiaryHistory(mockDiaryRepository)
    }

    @Test
    fun searchForProducts_ResourceSuccess() = runTest{
        val result = getDiaryHistory()
        assertTrue(result is Resource.Success)
    }

}