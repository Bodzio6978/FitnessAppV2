package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ScannerScreen() {


    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
        factory = {

                  Barco

        },
        update = {

        }
    )


}