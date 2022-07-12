package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class SearchForProductsTest{

    @Mock
    private lateinit var mockDiaryRepository: DiaryRepository

    private lateinit var searchForProducts: SearchForProducts

    @Before
    fun setUp() = runTest{
        mockDiaryRepository = Mockito.mock(DiaryRepository::class.java)
        Mockito.`when`(mockDiaryRepository.searchForProducts(Mockito.anyString())).thenReturn(
            Resource.Success(data = emptyList())
        )
        Mockito.`when`(mockDiaryRepository.getLocalProductHistory()).thenReturn(
            Resource.Error(uiText = "")
        )
        searchForProducts = SearchForProducts(
            diaryRepository = mockDiaryRepository,
            resourceProvider = ResourceProvider(RuntimeEnvironment.getApplication()),
            getDiaryHistory = GetDiaryHistory(mockDiaryRepository)
        )
    }

    @Test
    fun searchForProducts_ResourceSuccess() = runTest{
        val result = searchForProducts("abc")
        assertTrue(result is Resource.Success)
    }

    @Test
    fun emptyProductName_ResourceError() = runTest{
        val result = searchForProducts("")
        assertTrue(result is Resource.Error)
    }

}