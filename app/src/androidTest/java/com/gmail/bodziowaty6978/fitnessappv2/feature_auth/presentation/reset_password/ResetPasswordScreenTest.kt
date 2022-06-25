package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.reset_password

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
internal class ResetPasswordScreenTest {
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
                    startDestination = AuthScreen.ResetPasswordAuthScreen.route
                ) {
                    composable(route = AuthScreen.ResetPasswordAuthScreen.route) {
                        ResetPasswordScreen()
                    }
                }
            }
        }
    }

    @Test
    fun enteredEmail_TextIsDisplayed(){
        val text = "abc"
        val loginEmail = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.EMAIL))
        loginEmail.assertIsDisplayed().performTextInput(text)
        loginEmail.assert(
            hasText(text)
        )
    }

    @Test
    fun emptyFields_SnackbarVisible(){
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.EMAIL)).performTextClearance()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.BUTTON)).performClick()
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.please_enter_your_email_in_order_to_reset_your_password))
    }

}