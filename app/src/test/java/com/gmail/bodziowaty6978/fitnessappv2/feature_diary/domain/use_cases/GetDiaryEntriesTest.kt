package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.diary.GetDiaryEntries
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.diary.SortDiaryEntries
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class GetDiaryEntriesTest{

    @Mock
    private lateinit var mockDiaryRepository: DiaryRepository

    private lateinit var closable:AutoCloseable
    private lateinit var getDiaryEntries: GetDiaryEntries
    private lateinit var sortDiaryEntries: SortDiaryEntries
    private lateinit var resourceProvider: ResourceProvider

    @Before
    fun setUp(){
        closable = MockitoAnnotations.openMocks(this)
        mockDiaryRepository = Mockito.mock(DiaryRepository::class.java)
        sortDiaryEntries = SortDiaryEntries()
        getDiaryEntries = GetDiaryEntries(
            diaryRepository = mockDiaryRepository,
            sortDiaryEntries = sortDiaryEntries
        )
        resourceProvider = ResourceProvider(RuntimeEnvironment.getApplication())
    }

    @Test
    fun `call get entries with correct date, Resource Success`() = runTest{
        Mockito.`when`(mockDiaryRepository.getDiaryEntries(Date(System.currentTimeMillis()).toString())).thenReturn(
            Resource.Success(emptyList())
        )
        val result = getDiaryEntries(
            Date(System.currentTimeMillis()).toString(),
            resourceProvider.getStringArray(R.array.meal_names)
        )
        println(result.toString())
        assertTrue(result is Resource.Success)
    }

    @After
    fun after(){
        closable.close()
    }


}