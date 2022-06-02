package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.util

sealed class IntroductionExpectedQuestionAnswer{
    object TypeOfWork: IntroductionExpectedQuestionAnswer()
    object HowOftenDoYouTrain: IntroductionExpectedQuestionAnswer()
    object ActivityDuringADay: IntroductionExpectedQuestionAnswer()
    object Height: IntroductionExpectedQuestionAnswer()
    object Gender: IntroductionExpectedQuestionAnswer()
    object Age: IntroductionExpectedQuestionAnswer()
    object CurrentWeight: IntroductionExpectedQuestionAnswer()
    object DesiredWeight: IntroductionExpectedQuestionAnswer()
}
