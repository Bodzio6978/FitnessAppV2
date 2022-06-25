package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.register

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.di.AppModule
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.MainActivity
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.FitnessAppV2Theme
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.util.AuthScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
internal class RegisterScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            FitnessAppV2Theme {
                NavHost(
                    navController = navController,
                    startDestination = AuthScreen.RegisterAuthScreen.route
                ) {
                    composable(route = AuthScreen.RegisterAuthScreen.route) {
                        RegisterScreen()
                    }
                }
            }
        }
    }

    @Test
    fun enteredEmail_TextIsDisplayed(){
        val text = "email"
        val email = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.EMAIL))
        email.assertIsDisplayed().performTextInput(text)
        email.assert(
            hasText(text)
        )
    }

    @Test
    fun enteredUsername_TextIsDisplayed(){
        val text = "username"
        val username = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.USERNAME))
        username.assertIsDisplayed().performTextInput(text)
        username.assert(
            hasText(text)
        )
    }

    @Test
    fun enteredPassword_TextIsDisplayed(){
        val text = "password"
        val password = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.PASSWORD))
        password.assertIsDisplayed().performTextInput(text)
        val stringBuilder = StringBuilder()
        for (c in text.indices){
            stringBuilder.append("•")
        }
        password.assert(
            hasText(stringBuilder.toString())
        )
    }

    @Test
    fun enteredConfirmPassword_TextIsDisplayed(){
        val text = "confirm"
        val password = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.CONFIRM))
        password.assertIsDisplayed().performTextInput(text)
        val stringBuilder = StringBuilder()
        for (c in text.indices){
            stringBuilder.append("•")
        }
        password.assert(
            hasText(stringBuilder.toString())
        )
    }

    @Test
    fun emptyFields_SnackbarVisible(){
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.PASSWORD)).performTextClearance()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.EMAIL)).performTextClearance()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.USERNAME)).performTextClearance()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.CONFIRM)).performTextClearance()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.BUTTON)).performClick()
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.please_make_sure_all_fields_are_filled_in_correctly))
    }

}