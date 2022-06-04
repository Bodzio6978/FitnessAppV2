package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.bodziowaty6978.fitnessappv2.common.di.AppModule
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.MainActivity
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.FitnessAppV2Theme
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.BottomBarScreen
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton.CurrentDate
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository.DiaryRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class)
internal class DiaryScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var mealNames:List<String>

    @Before
    fun setUp(){
        hiltRule.inject()
        mealNames = ResourceProvider(composeRule.activity).getStringArray(R.array.meal_names)
        composeRule.setContent {
            val navController = rememberNavController()
            FitnessAppV2Theme {
                NavHost(navController = navController, startDestination = BottomBarScreen.Diary.route){
                    composable(route = BottomBarScreen.Diary.route){
                        DiaryScreen()
                    }
                }
            }
        }
    }

    @Test
    fun diaryScreenComposed_EveryMealSectionVisible(){
        mealNames.forEach {
            composeRule.onNodeWithTag(it).assertIsDisplayed()
        }
    }

    @Test
    fun diaryScreenComposed_TodayDisplayedInsteadOfDate(){
        val calendarSectionDate = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.CALENDAR_DATE))
        calendarSectionDate.assertIsDisplayed().assert(
            hasText(composeRule.activity.getString(R.string.today))
        )
    }

    @Test
    fun diaryScreenComposed_YesterdayDisplayedInsteadOfDate(){
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.CALENDAR_BACK)).performClick()
        val calendarSectionDate = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.CALENDAR_DATE))
        calendarSectionDate.assertIsDisplayed().assert(
            hasText(composeRule.activity.getString(R.string.yesterday))
        )
        CurrentDate.addDay()
    }

    @Test
    fun diaryScreenComposed_TomorrowDisplayedInsteadOfDate(){
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.CALENDAR_FORWARD)).performClick()
        val calendarSectionDate = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.CALENDAR_DATE))
        calendarSectionDate.assertIsDisplayed().assert(
            hasText(composeRule.activity.getString(R.string.tomorrow))
        )
        CurrentDate.deductDay()
    }
}