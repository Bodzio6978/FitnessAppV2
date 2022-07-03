package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel(){

    private val _weightState = mutableStateOf("")
    val weightState : State<String> = _weightState

    fun onEvent(event:ProductEvent){
        when(event){
            is ProductEvent.EnteredWeight -> {
                _weightState.value = event.value
            }
            is ProductEvent.ClickedWeightButton -> {
                _weightState.value = event.value.toString()
            }
            is ProductEvent.ClickedAddProduct -> {

            }
            is ProductEvent.ClickedBackArrow -> {
                navigator.navigate(NavigationActions.General.navigateUp())
            }
        }

    }

}