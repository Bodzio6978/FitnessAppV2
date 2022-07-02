package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search

import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId

sealed class SearchState{
    object Loading:SearchState()
    data class Error(val message:String):SearchState()
    data class Success(val products:List<ProductWithId> = emptyList()):SearchState()
}
