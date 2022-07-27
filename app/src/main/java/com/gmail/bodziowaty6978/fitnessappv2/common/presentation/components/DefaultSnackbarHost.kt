package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun DefaultSnackbarHost(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(snackbarHostState) { data ->
        // custom snackbar with the custom colors
        Snackbar(
            actionColor = MaterialTheme.colors.primary,
            snackbarData = data,
            backgroundColor = MaterialTheme.colors.onSurface,
            contentColor = MaterialTheme.colors.surface
        )
    }
}