package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.BackArrow
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.components.ContainerWeightSection
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.components.NutritionSection
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.components.TextFieldSection

@Composable
fun NewProductScreen(
    mealName: String,
    viewModel: NewProductViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NewProductEvent.ClickedSaveButton)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save"
                )
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackArrow {
                viewModel.onEvent(NewProductEvent.ClickedBackArrow)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldSection(
                name = stringResource(id = R.string.product_name),
                textFieldValue = state.productName,
                onTextEntered = {
                    viewModel.onEvent(NewProductEvent.EnteredProductName(it))
                },
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.6).dp)
                    .testTag(stringResource(id = R.string.product_name)+"TEXT_FIELD")
            )

            Spacer(modifier = Modifier.height(16.dp))


            ContainerWeightSection(
                state = state,
                onEvent = {
                    viewModel.onEvent(it)
                },
                containerWeightText = state.containerWeight
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Nutrition values",
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Normal
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            NutritionSection(
                onEvent = {
                    viewModel.onEvent(it)
                },
                state = state
            )
        }
    }
}