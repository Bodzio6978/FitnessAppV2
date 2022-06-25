package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.di.AppModule
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavHostGraph
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.MainActivity
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.FitnessAppV2Theme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class)
@RunWith(MockitoJUnitRunner::class)
internal class SearchScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var navigator:Navigator

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            FitnessAppV2Theme {
                NavHostGraph(
                    navigator = navigator
                )
            }
        }
        navigator.navigate(NavigationActions.DiaryScreen.diaryToSearch("Breakfast"))
    }

    @Test
    fun onSearchTextEntered_TextVisible(){
        println(navigator.navActions.value?.destination.toString())
        val text = "abc"
        val textField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.TEXT_FIELD))
        textField.assertIsDisplayed().performTextClearance()
        textField.performTextInput(text)
        textField.assert(hasText(text))
    }
}