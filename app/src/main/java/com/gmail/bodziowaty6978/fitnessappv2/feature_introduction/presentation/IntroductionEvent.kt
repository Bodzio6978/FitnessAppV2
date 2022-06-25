package com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation

import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation.util.IntroductionExpectedQuestionAnswer

sealed class IntroductionEvent {
    data class ClickedArrow(val isForward:Boolean): IntroductionEvent()
    data class EnteredAnswer(val expectedQuestionAnswer: IntroductionExpectedQuestionAnswer, val answer:String):
        IntroductionEvent()
    object FinishIntroduction: IntroductionEvent()
}