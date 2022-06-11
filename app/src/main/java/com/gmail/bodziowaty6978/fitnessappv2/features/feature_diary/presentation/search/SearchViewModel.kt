package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.TextFieldState
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases.SearchDiaryUseCases
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases.SearchForProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val navigator: Navigator,
    private val searchDiaryUseCases: SearchDiaryUseCases,
    private val resourceProvider: ResourceProvider
): ViewModel(){
    
    private val _searchBarState = mutableStateOf(
        TextFieldState(
            hint = resourceProvider.getString(R.string.product_name)
        )
    )
    val searchBarState : State<TextFieldState> = _searchBarState

    private val _searchState = mutableStateOf<List<Product>>(emptyList())
    val searchState : State<List<Product>> = _searchState


    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.ClickedBackArrow -> {
                navigator.navigate(NavigationActions.General.navigateUp())
            }
            is SearchEvent.ClickedSearch -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = searchDiaryUseCases.searchForProducts(event.searchText)
                }
            }
            is SearchEvent.EnteredSearchText -> {
                _searchBarState.value = searchBarState.value.copy(
                    text = event.text
                )
                _searchBarState.value = searchBarState.value.copy(
                    isHintVisible = searchBarState.value.text.isEmpty()
                )
            }
            is SearchEvent.ClickedSearchItem -> {

            }
        }
    }
    fun initializeHistory(){
        viewModelScope.launch(Dispatchers.IO) {
            val history = searchDiaryUseCases.getDiaryHistory()
            _searchState.value = history
        }

    }
}