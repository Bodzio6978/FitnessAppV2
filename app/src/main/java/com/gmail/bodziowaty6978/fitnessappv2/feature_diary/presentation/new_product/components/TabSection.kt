package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.DarkGreyElevation

@Composable
fun TabSection(
    unitIndex: Int = 0,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = DarkGreyElevation,
        contentColor = Color.White,
    ) {

        Tab(
            selected = selectedTabIndex == 0,
            onClick = {
                onTabSelected(0)
            },
            text = {
                Text(
                    text = if (unitIndex == 0) stringResource(id = R.string.in_100g)
                    else stringResource(id = R.string.in_100ml),
                    color = Color.White,
                    style = MaterialTheme.typography.button
                )
            },
            modifier = Modifier
                .testTag(stringResource(id = R.string.in_100g))
        )

        Tab(
            selected = selectedTabIndex == 1,
            onClick = {
                onTabSelected(1)
            },
            text = {
                Text(
                    text = stringResource(id = R.string.in_container),
                    color = Color.White,
                    style = MaterialTheme.typography.button
                )
            },
            modifier = Modifier
                .testTag(stringResource(id = R.string.in_container))
        )

        Tab(
            selected = selectedTabIndex == 2,
            onClick = {
                onTabSelected(2)
            },
            text = {
                Text(
                    text = stringResource(id = R.string.in_average),
                    color = Color.White,
                    style = MaterialTheme.typography.button
                )
            },
            modifier = Modifier
                .testTag(stringResource(id = R.string.in_average))
        )
    }
}