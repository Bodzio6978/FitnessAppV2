package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util

import androidx.compose.ui.focus.FocusState

data class TextFieldState (
    val text:String = "",
    val hint:String = "",
    val isHintVisible:Boolean = true,
)