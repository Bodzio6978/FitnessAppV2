package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun BackArrow(
    onBackArrowPressed:() -> Unit
) {

    IconButton(
        onClick = {
            onBackArrowPressed()
        }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "arrow back"
        )
    }

}