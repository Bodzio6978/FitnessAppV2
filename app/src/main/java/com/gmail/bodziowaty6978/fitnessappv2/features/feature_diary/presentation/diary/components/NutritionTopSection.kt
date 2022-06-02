package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey

@Composable
fun NutritionTopSection() {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp),
    ) {

        Text(
            text = "",
            style = MaterialTheme.typography.body2,
            color = TextGrey,
            modifier = Modifier.weight(1F),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Carbs",
            style = MaterialTheme.typography.body2,
            color = TextGrey,
            modifier = Modifier.weight(1F),
            textAlign = TextAlign.Center

        )

        Text(
            text = "Protein",
            style = MaterialTheme.typography.body2,
            color = TextGrey,
            modifier = Modifier.weight(1F),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Fat",
            style = MaterialTheme.typography.body2,
            color = TextGrey,
            modifier = Modifier.weight(1F),
            textAlign = TextAlign.Center
        )

    }
}