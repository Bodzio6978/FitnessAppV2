package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.NewProductEvent
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.NewProductState

@Composable
fun NutritionSection(
    state:NewProductState,
    onEvent:(NewProductEvent) -> Unit,
) {

    Card(
        shape = RoundedCornerShape(10),
        elevation = 3.dp,
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TabSection(
                selectedTabIndex = state.nutritionSelectedTabIndex,
                onTabSelected = {
                    onEvent(NewProductEvent.ClickedNutritionTab(it))
                },
                unitIndex = state.dropDownSelectedIndex
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextFieldSection(
                name = stringResource(id = R.string.calories) + "(${stringResource(id = R.string.kcal).lowercase()})",
                textFieldValue = state.calories,
                onTextEntered = {
                    onEvent(NewProductEvent.EnteredCalories(it))
                },
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.3).dp)
                    .testTag(stringResource(id = R.string.calories)+"TEXT_FIELD")
                ,
                textFieldOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextFieldSection(
                name = stringResource(id = R.string.carbohydrates_g),
                textFieldValue = state.carbohydrates,
                onTextEntered = {
                    onEvent(NewProductEvent.EnteredCarbohydrates(it))
                },
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.3).dp)
                    .testTag(stringResource(id = R.string.carbohydrates)+"TEXT_FIELD"),
                textFieldOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextFieldSection(
                name = stringResource(id = R.string.protein_g),
                textFieldValue = state.protein,
                onTextEntered = {
                    onEvent(NewProductEvent.EnteredProtein(it))
                },
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.3).dp)
                    .testTag(stringResource(id = R.string.protein)+"TEXT_FIELD"),
                textFieldOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextFieldSection(
                name = stringResource(id = R.string.fat_g),
                textFieldValue = state.fat,
                onTextEntered = {
                    onEvent(NewProductEvent.EnteredFat(it))
                },
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.3).dp)
                    .testTag(stringResource(id = R.string.fat)+"TEXT_FIELD"),
                textFieldOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}