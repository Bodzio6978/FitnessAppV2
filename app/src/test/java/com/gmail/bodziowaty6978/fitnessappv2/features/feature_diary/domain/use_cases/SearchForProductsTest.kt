package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock
import org.mockito.Mockito


@OptIn(ExperimentalCoroutinesApi::class)
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
        searchForProducts = SearchForProducts(mockDiaryRepository)
    }

    @Test
    fun searchForProducts_ResourceSuccess() = runTest{
        val result = searchForProducts("abc")
        assertTrue(result is Resource.Success)
    }

}