package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search

sealed class SearchEvent{
    object ClickedBackArrow:SearchEvent()
    object ClickedSearch:SearchEvent()
}
