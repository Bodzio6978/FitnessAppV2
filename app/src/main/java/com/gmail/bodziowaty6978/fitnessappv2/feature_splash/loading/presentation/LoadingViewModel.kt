package com.gmail.bodziowaty6978.fitnessappv2.feature_splash.loading.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val navigator: Navigator
): ViewModel(){

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState())

    fun onEvent(event: LoadingEvent){
        Log.e(TAG,event.toString())
        when(event){
            is LoadingEvent.ReceivedNutrition -> {
                _loadingState.value = _loadingState.value.copy(
                    hasNutritionValues = event.nutritionValues.calories!=0
                )
                checkUser()
            }
            is LoadingEvent.ReceivedInformation -> {
                _loadingState.value = _loadingState.value.copy(
                    hasInformation = event.userInformation.age!=0
                )
                checkUser()
            }
        }
    }

    private fun checkUser(){
        val isLogged = _loadingState.value.isLoggedIn
        isLogged?.let {
            if (isLogged) {
                val hasInformation = _loadingState.value.hasInformation
                val hasNutrition = _loadingState.value.hasNutritionValues
                if (hasInformation != null && hasNutrition != null) {
                    if (!hasInformation || !hasNutrition) {
                        navigator.navigate(NavigationActions.LoadingScreen.loadingToIntroduction())
                    } else {
                        navigator.navigate(NavigationActions.LoadingScreen.loadingToSummary())
                    }
                }

            } else {
                navigator.navigate(NavigationActions.LoadingScreen.loadingToLogin())
            }
        }
    }

    fun checkIfUserLoggedIn(){
        _loadingState.value = _loadingState.value.copy(
            isLoggedIn = firebaseAuth.currentUser!=null
        )
        checkUser()
    }
}