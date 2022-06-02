package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Grey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Yellow

@Composable
fun Tile(
    content:String,
    isSelected:Boolean,
    onItemClick:() -> Unit
) {

    OutlinedButton(
        onClick = {
            onItemClick()
        },
        border = BorderStroke(1.dp, Yellow),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (isSelected) Color.Black else Yellow,
            backgroundColor = if (isSelected) Yellow else Grey
        )

    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.button,
//            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) Color.Black else Yellow,
            textAlign = TextAlign.Center
        )
    }
}