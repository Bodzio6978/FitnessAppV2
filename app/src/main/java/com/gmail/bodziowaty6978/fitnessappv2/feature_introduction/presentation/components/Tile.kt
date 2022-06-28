package com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primaryVariant,
            backgroundColor = if (isSelected) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.background
        ),
        elevation = ButtonDefaults.elevation(4.dp)
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.button,
            color = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primaryVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 2.dp)
        )
    }
}