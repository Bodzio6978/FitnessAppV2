package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.TextFieldState
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.SearchDiaryUseCases
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

    private val _searchState = mutableStateOf<SearchState>(SearchState.Success())
    val searchState : State<SearchState> = _searchState


    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.ClickedBackArrow -> {
                navigator.navigate(NavigationActions.General.navigateUp())
            }
            is SearchEvent.ClickedSearch -> {
                _searchState.value = SearchState.Loading
                viewModelScope.launch(Dispatchers.IO) {
                    val result = searchDiaryUseCases.searchForProducts(event.searchText)
                    if(result is Resource.Error){
                        _searchState.value = SearchState.Error(result.uiText.toString())
                    }else{
                        val data = result.data!!
                        _searchState.value = SearchState.Success(products = data)
                    }
                }
            }
            is SearchEvent.EnteredSearchText -> {
                _searchBarState.value = searchBarState.value.copy(
                    text = event.text
                )
            }
            is SearchEvent.ClickedSearchItem -> {
                val clickedItem = event.item
                navigator.navigate(NavigationActions.SearchScreen.searchToProduct(clickedItem))
            }
        }
    }
    fun initializeHistory(){
        viewModelScope.launch(Dispatchers.IO) {
            val history = searchDiaryUseCases.getDiaryHistory()
            _searchState.value = SearchState.Success(history.data!!)
        }
    }
}