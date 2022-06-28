package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NutritionBottomItem(
    currentValue: Int,
    wantedValue: Int,
    name: String,
    modifier: Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 10.dp)
    ) {

        Text(
            text = name,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(bottom = 3.dp, top = 3.dp)
        )

        LinearProgressIndicator(
            progress = currentValue.toFloat() / wantedValue.toFloat(),
            color = MaterialTheme.colors.primaryVariant
        )

        Text(
            modifier = Modifier.padding(top = 3.dp),
            text = "$currentValue/$wantedValue",
            style = MaterialTheme.typography.body2,
        )

    }

}