package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation

sealed class IntroductionUiEvent{
    object MoveForward: IntroductionUiEvent()
    object MoveBackward: IntroductionUiEvent()
    data class ShowSnackbar(val message:String): IntroductionUiEvent()
}
