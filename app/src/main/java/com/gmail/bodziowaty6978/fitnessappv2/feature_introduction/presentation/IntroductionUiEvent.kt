package com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation

sealed class IntroductionUiEvent{
    object MoveForward: IntroductionUiEvent()
    object MoveBackward: IntroductionUiEvent()
}
