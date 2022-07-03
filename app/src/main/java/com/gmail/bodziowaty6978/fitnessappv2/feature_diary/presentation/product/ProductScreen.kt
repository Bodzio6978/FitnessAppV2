package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId

@Composable
fun ProductScreen(
    productWithId: ProductWithId
) {

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(id = R.string.add))
                },
                onClick = {
                    /*TODO*/
                },
                icon = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            )
        }
    ) {

    }

    Text(text = productWithId.product.name)

}