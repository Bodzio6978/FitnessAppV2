package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey


@Composable
fun MealSectionNutritionItem(
    currentValue: Int,
    wantedValue:Int,
    name: String,
    modifier: Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalProgressIndicator(
            progress = (currentValue.toDouble()/wantedValue.toDouble()*100.0).toFloat()
        )
        
        Spacer(modifier = Modifier.width(6.dp))

        Column() {
            Text(
                text = currentValue.toString(),
                style = MaterialTheme.typography.body1
            )

            Text(
                text = name,
                style = MaterialTheme.typography.body2.copy(
                    color = TextGrey
                )
            )
        }
    }
}