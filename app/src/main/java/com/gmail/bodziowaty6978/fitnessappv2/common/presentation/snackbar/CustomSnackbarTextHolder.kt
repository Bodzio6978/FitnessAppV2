package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.snackbar

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.snackbar.SnackbarTextHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CustomSnackbarTextHolder:SnackbarTextHolder {
    private val _snackbarText: MutableStateFlow<String?> by lazy {
        MutableStateFlow(null)
    }

    override val snackbarText: StateFlow<String?> = _snackbarText.asStateFlow()

    override fun showSnackbarText(text: String?) {
        _snackbarText.update {
            text
        }
    }
}