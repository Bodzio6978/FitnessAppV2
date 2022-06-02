package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.util.IntroductionExpectedQuestionAnswer
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.repository.IntroductionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class SaveIntroductionInformationTest {

    private lateinit var saveIntroductionInformation: SaveIntroductionInformation
    private lateinit var answers: MutableMap<IntroductionExpectedQuestionAnswer, String>

    @Mock
    private lateinit var mockIntroductionRepository: IntroductionRepository

    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp() = runTest{
        closeable = MockitoAnnotations.openMocks(this)
        mockIntroductionRepository = Mockito.mock(IntroductionRepository::class.java)
        Mockito.`when`(mockIntroductionRepository.saveIntroductionInformation(
            any(),
            any()
        )).thenReturn(Result.Success)
        saveIntroductionInformation = SaveIntroductionInformation(
            calculateNutritionValues = CalculateNutritionValues(),
            resourceProvider = ResourceProvider(RuntimeEnvironment.getApplication()),
            introductionRepository = mockIntroductionRepository
        )
        answers = mutableMapOf(
            IntroductionExpectedQuestionAnswer.Gender to "0",
            IntroductionExpectedQuestionAnswer.Age to "18",
            IntroductionExpectedQuestionAnswer.Height to "180.0",
            IntroductionExpectedQuestionAnswer.CurrentWeight to "80.0",
            IntroductionExpectedQuestionAnswer.TypeOfWork to "0",
            IntroductionExpectedQuestionAnswer.HowOftenDoYouTrain to "0",
            IntroductionExpectedQuestionAnswer.TypeOfWork to "0",
            IntroductionExpectedQuestionAnswer.DesiredWeight to "80.0",
            IntroductionExpectedQuestionAnswer.ActivityDuringADay to "0"
        )
    }

    @Test
    fun `passed empty currentWeight, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.CurrentWeight] = ""
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed empty wantedWeight, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.DesiredWeight] = ""
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed empty age, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.Age] = ""
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed empty height, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.Height] = ""
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed letters as currentWeight, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.CurrentWeight] = "abc"
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed letters as wantedWeight, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.DesiredWeight] = "abc"
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed letters as age, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.Age] = "abc"
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed letters as height, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.Height] = "abc"
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed negative number as height, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.Height] = "-50"
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed negative number as age, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.Height] = "-50"
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed negative number as wantedWeight, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.Height] = "-50"
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed negative number as currentWeight, return Result Error`() = runTest {
        answers[IntroductionExpectedQuestionAnswer.Height] = "-50"
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `passed correct data, Result Success`() = runTest{
        val result = saveIntroductionInformation(answers)
        assertTrue(result is Result.Success)
    }

    @After
    fun after(){
        closeable.close()
    }

}