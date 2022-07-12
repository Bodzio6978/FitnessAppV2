package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any

@OptIn(ExperimentalCoroutinesApi::class)
internal class SaveProductToHistoryTest {

    @Mock
    private lateinit var mockDiaryRepository: DiaryRepository

    private lateinit var closable: AutoCloseable
    private lateinit var saveProductToHistory: SaveProductToHistory

    @Before
    fun setUp() = runTest {
        closable = MockitoAnnotations.openMocks(this)
        mockDiaryRepository = Mockito.mock(DiaryRepository::class.java)
        Mockito.`when`(mockDiaryRepository.saveProductToHistory(any())).thenReturn(CustomResult.Success)
        saveProductToHistory = SaveProductToHistory(
            diaryRepository = mockDiaryRepository,
        )
    }

    @Test
    fun saveProduct_ResultSuccess() = runTest{
        val result = saveProductToHistory(ProductWithId(productId = "", product = Product()))
        assert(result is CustomResult.Success)
    }

    @After
    fun after(){
        closable.close()
    }
}