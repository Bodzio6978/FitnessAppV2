package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductNutritionSection(
    nutritionData: NutritionData
) {

    Card(
        elevation = 3.dp,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(15)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {

            ChartSection(
                nutritionData = nutritionData,
            )
        }

    }









}