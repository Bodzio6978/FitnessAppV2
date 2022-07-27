package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.di.AppModule
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.navigation.NavHostGraph
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.navigation.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.MainActivity
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.FitnessAppV2Theme
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.Screen
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
internal class ProductScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var navigator: Navigator

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            FitnessAppV2Theme {
                NavHostGraph(
                    navigator = navigator,
                    startDestination = Screen.ProductScreen.route + "?mealName={mealName}"
                )
            }
        }
    }

    @Test
    fun weightEntered_TextUpdates(){
        val text = "12"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.WEIGHT_TEXT_FIELD))
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text))
    }

    @Test
    fun clickedWeightButton_WeightUpdated(){
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.WEIGHT_TEXT_FIELD))
        for (i in 50..200 step 50){
            val weightButton = composeRule.onNodeWithTag(i.toString()+composeRule.activity.getString(R.string.WEIGHT_BUTTON))
            weightTextField.performTextClearance()
            weightButton.performClick()
            weightTextField.assert(hasText(i.toString()))
        }
    }

    @Test
    fun clickedAddProductButton_EmptyWeight_SnackbarVisible(){
        val addProductButton = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.add_product))
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.WEIGHT_TEXT_FIELD))
        weightTextField.performTextClearance()
        addProductButton.performClick()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.incorrect_weight_was_entered))
    }

    @Test
    fun clickedAddProductButton_IncorrectWeight_SnackbarVisible(){
        val addProductButton = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.add_product))
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.WEIGHT_TEXT_FIELD))
        weightTextField.performTextClearance()
        weightTextField.performTextInput("abc")
        addProductButton.performClick()
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.incorrect_weight_was_entered))
    }
}