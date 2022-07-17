package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.di.AppModule
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavHostGraph
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
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
internal class NewProductScreenTest {

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
                    startDestination = Screen.NewProductScreen.route + "?mealName={mealName}"
                )
            }
        }
    }

    @Test
    fun containerWeightEntered_TextUpdates(){
        val text = "12"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.container_weight)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text))
    }

    @Test
    fun productNameEntered_TextUpdates(){
        val text = "abc"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.product_name)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text))
    }

    @Test
    fun caloriesEntered_TextUpdates(){
        val text = "12"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.calories)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text))
    }


    @Test
    fun carbohydratesEntered_TextUpdates(){
        val text = "12.8"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.carbohydrates)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text))
    }

    @Test
    fun proteinEntered_TextUpdates(){
        val text = "12.8"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.protein)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text))
    }

    @Test
    fun fatEntered_TextUpdates(){
        val text = "12.8"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.fat)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text))
    }

    @Test
    fun carbohydratesWithCommaEntered_CommaReplacedWithDot(){
        val text = "12,8"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.carbohydrates)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text.replace(",",".")))
    }

    @Test
    fun proteinWithCommaEntered_CommaReplacedWithDot(){
        val text = "12,8"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.protein)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text.replace(",",".")))
    }

    @Test
    fun fatWithCommaEntered_CommaReplacedWithDot(){
        val text = "12,8"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.fat)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text.replace(",",".")))
    }

    @Test
    fun caloriesWithCommaEntered_CommaReplacedWithNothing(){
        val text = "12,8"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.calories)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text.replace(",","")))
    }

    @Test
    fun caloriesWithDotEntered_CommaReplacedWithNothing(){
        val text = "12.8"
        val weightTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.calories)+"TEXT_FIELD")
        weightTextField.assertIsDisplayed().performTextClearance()
        weightTextField.performTextInput(text)
        weightTextField.assert(hasText(text.replace(".","")))
    }

    @Test
    fun clickDropdownMenu_DropdownMenuExpanded(){
        val dropdownMenuItems = composeRule.activity.resources.getStringArray(R.array.units).toMutableList()
        dropdownMenuItems.removeAt(0)
        val dropDownMenu = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.container_weight)+"DROPDOWN_MENU")
        dropDownMenu.performClick()
        dropdownMenuItems.forEach {
            composeRule.onNodeWithText(it).assertIsDisplayed()
        }
    }

    @Test
    fun clickDropdownMenuItems_DropdownMenuItemChanged(){
        val dropdownMenuItems = composeRule.activity.resources.getStringArray(R.array.units).toMutableList()
        val dropDownMenu = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.container_weight)+"DROPDOWN_MENU")

        composeRule.onNodeWithText(dropdownMenuItems[0]).assertIsDisplayed()
        dropdownMenuItems.removeAt(0)

        dropDownMenu.performClick()
        dropdownMenuItems.forEach {
            composeRule.onNodeWithText(it).assertIsDisplayed()
            composeRule.onNodeWithText(it).performClick()
            composeRule.onNodeWithText(it).assertIsDisplayed()
            dropDownMenu.performClick()
        }
    }

    @Test
    fun in100TabSelected_ContainerWeightTextChanged(){
        val in100Tab = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.in_100g))
        in100Tab.performClick()
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.container_weight)).assertIsDisplayed()
    }

    @Test
    fun inContainer_ContainerWeightTextChanged(){
        val inContainer = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.in_container))
        inContainer.performClick().performClick()
        Thread.sleep(10000)
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.container_weight)+"*").assertIsDisplayed()
    }

    @Test
    fun inAverage_ContainerWeightTextChanged(){
        val inAverage = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.in_average))
        inAverage.performClick().performClick()
        Thread.sleep(10000)
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.average_weight)+"*").assertIsDisplayed()
    }

    @Test
    fun mlSelectedFromDropdownMenu_In100mlVisible(){
        val dropdownMenuItems = composeRule.activity.resources.getStringArray(R.array.units).toMutableList()
        val dropDownMenu = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.container_weight)+"DROPDOWN_MENU")

        dropDownMenu.performClick()
        composeRule.onNodeWithTag(dropdownMenuItems[1]+"DROPDOWN_MENU_ITEM").performClick()

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.in_100ml)).assertIsDisplayed()
    }

    @Test
    fun gSelectedFromDropdownMenu_In100gVisible(){
        val dropdownMenuItems = composeRule.activity.resources.getStringArray(R.array.units).toMutableList()
        val dropDownMenu = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.container_weight)+"DROPDOWN_MENU")

        dropDownMenu.performClick()
        composeRule.onNodeWithTag(dropdownMenuItems[0]+"DROPDOWN_MENU_ITEM").performClick()

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.in_100g)).assertIsDisplayed()
    }
}