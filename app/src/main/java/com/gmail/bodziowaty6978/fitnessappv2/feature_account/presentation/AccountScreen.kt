package com.gmail.bodziowaty6978.fitnessappv2.feature_account.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components.HorizontalProgressIndicator

@Composable
fun AccountScreen(
) {
    
    Text(text = "account")
    
    HorizontalProgressIndicator(progress = 79F,
    modifier = Modifier
        .height(32.dp))

}