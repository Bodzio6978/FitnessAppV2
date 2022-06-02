package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation

import android.content.Context
import android.util.Log
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.di.AppModule
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.di.IntroductionModule
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.MainActivity
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.FitnessAppV2Theme
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.di.AuthModule
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.di.DiaryModule
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.util.IntroductionExpectedQuestionAnswer
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.Delay
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@HiltAndroidTest
@UninstallModules(AppModule::class, IntroductionModule::class, AuthModule::class, DiaryModule::class)
internal class IntroductionScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var context: Context
    private var expectedAnswersSize = IntroductionExpectedQuestionAnswer::class.nestedClasses.size

    @Before
    fun setUp() {
        hiltRule.inject()
        context = ApplicationProvider.getApplicationContext()
        composeRule.setContent {
            val navController = rememberNavController()
            FitnessAppV2Theme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.IntroductionScreen.route
                ) {
                    composable(
                        route = Screen.IntroductionScreen.route
                    ) {
                        IntroductionScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun clickNext_PagerItemChanged() {
        composeRule.onNodeWithText(
            context.getString(R.string.what_is_your_gender)
        ).assertIsDisplayed()
        composeRule.onNodeWithTag(context.getString(R.string.NEXT)).performClick()
        composeRule.onNodeWithText(
            context.getString(R.string.what_is_your_age)
        ).assertIsDisplayed()
    }

    @Test
    fun clickNextAndBack_BackHidingProperly() {
        composeRule.onNodeWithTag(context.getString(R.string.BACK)).assertDoesNotExist()
        composeRule.onNodeWithTag(context.getString(R.string.NEXT)).performClick()
        composeRule.onNodeWithTag(context.getString(R.string.BACK)).assertIsDisplayed()
            .performClick().assertDoesNotExist()
    }

    @Test
    fun insertTextIntoAgeQuestionField_AgeFieldHasText(){
        val text = "abc"
        var hasBeenChecked = false
        for (i in 0..expectedAnswersSize) {
            if (!hasBeenChecked){
                try {
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.Age.TAG).assertIsDisplayed()
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.Age.TAG).performTextClearance()
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.Age.TAG).performTextInput(text)
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.Age.TAG)
                        .assert(hasText(text, ignoreCase = true))
                    hasBeenChecked = true
                } catch (e: AssertionError) {
                    composeRule.onNodeWithTag(context.getString(R.string.NEXT)).performClick()
                }
            }
        }
        if (!hasBeenChecked){
            assert(false)
        }
    }

    @Test
    fun insertTextIntoWeightQuestionField_WeightFieldHasText(){
        val text = "abc"
        var hasBeenChecked = false
        for (i in 0..expectedAnswersSize) {
            if (!hasBeenChecked){
                try {
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.CurrentWeight.TAG).assertIsDisplayed()
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.CurrentWeight.TAG).performTextClearance()
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.CurrentWeight.TAG).performTextInput(text)
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.CurrentWeight.TAG)
                        .assert(hasText(text, ignoreCase = true))
                    hasBeenChecked = true
                } catch (e: AssertionError) {
                    composeRule.onNodeWithTag(context.getString(R.string.NEXT)).performClick()
                }
            }
        }
        if (!hasBeenChecked){
            assert(false)
        }
    }

    @Test
    fun insertTextIntoDesiredWeightQuestionField_DesiredWeightFieldHasText(){
        val text = "abc"
        var hasBeenChecked = false
        for (i in 0..expectedAnswersSize) {
            if (!hasBeenChecked){
                try {
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.DesiredWeight.TAG).assertIsDisplayed()
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.DesiredWeight.TAG).performTextClearance()
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.DesiredWeight.TAG).performTextInput(text)
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.DesiredWeight.TAG)
                        .assert(hasText(text, ignoreCase = true))
                    hasBeenChecked = true
                } catch (e: AssertionError) {
                    composeRule.onNodeWithTag(context.getString(R.string.NEXT)).performClick()
                }
            }
        }
        if (!hasBeenChecked){
            assert(false)
        }
    }

    @Test
    fun insertTextIntoHeightQuestionField_AgeFieldHasText(){
        val text = "abc"
        var hasBeenChecked = false
        for (i in 0..expectedAnswersSize) {
            if (!hasBeenChecked){
                try {
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.Height.TAG).assertIsDisplayed()
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.Height.TAG).performTextClearance()
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.Height.TAG).performTextInput(text)
                    composeRule.onNodeWithTag(IntroductionExpectedQuestionAnswer.Height.TAG)
                        .assert(hasText(text, ignoreCase = true))
                    hasBeenChecked = true
                } catch (e: AssertionError) {
                    composeRule.onNodeWithTag(context.getString(R.string.NEXT)).performClick()
                }
            }
        }
        if (!hasBeenChecked){
            assert(false)
        }
    }

    @Test
    fun endOfQuestions_NextButtonChangedTextToFinish(){
        for (i in 0 until expectedAnswersSize-1){
            composeRule.onNodeWithTag(context.getString(R.string.NEXT)).performClick()
        }
        composeRule.onNodeWithText(context.getString(R.string.finish)).assertIsDisplayed()
    }
}