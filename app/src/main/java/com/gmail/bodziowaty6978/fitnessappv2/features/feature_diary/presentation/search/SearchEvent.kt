package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Product

sealed class SearchEvent{
    object ClickedBackArrow:SearchEvent()
    data class ClickedSearch(val searchText:String):SearchEvent()
    data class EnteredSearchText(val text:String):SearchEvent()
    data class ClickedSearchItem(val item:Product):SearchEvent()
}
