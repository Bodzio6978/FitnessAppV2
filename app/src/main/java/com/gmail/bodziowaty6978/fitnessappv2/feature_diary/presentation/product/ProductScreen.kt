package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton.CurrentDate
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.LightRed
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components.ProductNameSection
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components.ProductTopSection


@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    productWithId: ProductWithId,
    mealName: String
) {
    val product = productWithId.product
    val weightState = viewModel.weightState

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = stringResource(id = R.string.add_product).uppercase(),
                        color = Color.Black,
                        style = MaterialTheme.typography.button
                    )
                },
                onClick = {
                    /*TODO*/
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                },
                backgroundColor = LightRed
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProductTopSection(
                mealName = mealName,
                currentDate = CurrentDate.dateModel(LocalContext.current).valueToDisplay
                    ?: CurrentDate.dateModel(LocalContext.current).date,
                onEvent = {
                    viewModel.onEvent(it)
                }
            )

            ProductNameSection(
                currentWeight = weightState.value,
                productName = product.name,
                productBrand = product.brand,
                onEvent = {
                    viewModel.onEvent(it)
                }
            )

        }
    }
}
