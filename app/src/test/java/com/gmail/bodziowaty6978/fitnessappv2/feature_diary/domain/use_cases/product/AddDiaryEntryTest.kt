package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.DateModel
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class AddDiaryEntryTest{

    @Mock
    private lateinit var mockDiaryRepository: DiaryRepository

    private lateinit var closable:AutoCloseable
    private lateinit var addDiaryEntry: AddDiaryEntry

    @Before
    fun setUp() = runTest{
        closable = MockitoAnnotations.openMocks(this)
        mockDiaryRepository = Mockito.mock(DiaryRepository::class.java)
        Mockito.`when`(mockDiaryRepository.addDiaryEntry(any())).thenReturn(CustomResult.Success)
        addDiaryEntry = AddDiaryEntry(
            diaryRepository = mockDiaryRepository,
            resourceProvider = ResourceProvider(RuntimeEnvironment.getApplication())
        )
    }

    @Test
    fun nullWeight_ResultError() = runTest{
        val result = addDiaryEntry(
            productWithId = ProductWithId(productId = "", product = Product()),
            weight = null,
            dateModel = DateModel(),
            nutritionValues = NutritionValues(),
            mealName = ""
        )
        assert(result is CustomResult.Error)
    }

    @Test
    fun weight0_ResultError() = runTest{
        val result = addDiaryEntry(
            productWithId = ProductWithId(productId = "", product = Product()),
            weight = 0,
            dateModel = DateModel(),
            nutritionValues = NutritionValues(),
            mealName = ""
        )
        assert(result is CustomResult.Error)
    }

    @Test
    fun correctData_ResultSuccess() = runTest {
        val result = addDiaryEntry(
            productWithId = ProductWithId(productId = "", product = Product()),
            weight = 10,
            dateModel = DateModel(),
            nutritionValues = NutritionValues(),
            mealName = ""
        )
        assert(result is CustomResult.Success)
    }

    @After
    fun after(){
        closable.close()
    }
}