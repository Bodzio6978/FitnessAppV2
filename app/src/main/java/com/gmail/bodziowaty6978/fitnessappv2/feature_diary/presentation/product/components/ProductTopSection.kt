package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.ProductEvent

@Composable
fun ProductTopSection(
    mealName: String,
    currentDate: String,
    onEvent: (ProductEvent) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    onEvent(ProductEvent.ClickedBackArrow)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "arrow back"
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = mealName,
                    style = MaterialTheme.typography.h2
                )

                Text(
                    text = currentDate,
                    style = MaterialTheme.typography.body2,
                    color = TextGrey
                )

            }

            Text(
                text = stringResource(id = R.string.add).uppercase(),
                style = MaterialTheme.typography.button.copy(
                    fontSize = 16.sp,
                ),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable {
                        onEvent(ProductEvent.ClickedAddProduct)
                    }
            )

        }
    }
}