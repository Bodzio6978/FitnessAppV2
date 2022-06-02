package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun QuestionSection(
    title:String,
    content:@Composable () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )

            content()

        }
    }
}