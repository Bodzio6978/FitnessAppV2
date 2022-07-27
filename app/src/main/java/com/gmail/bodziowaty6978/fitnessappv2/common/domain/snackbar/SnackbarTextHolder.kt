package com.gmail.bodziowaty6978.fitnessappv2.common.domain.snackbar

import kotlinx.coroutines.flow.StateFlow

interface SnackbarTextHolder {
    val snackbarText: StateFlow<String?>
    fun showSnackbarText(text: String?)
}