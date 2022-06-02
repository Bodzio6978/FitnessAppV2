package com.gmail.bodziowaty6978.fitnessappv2.features.feature_splash.loading.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): ViewModel(){

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState())
    val loadingState: StateFlow<LoadingState> = _loadingState

    fun onEvent(event: LoadingEvent){
        Log.e(TAG,event.toString())
        when(event){
            is LoadingEvent.ReceivedNutrition -> {
                _loadingState.value = loadingState.value.copy(
                    hasNutritionValues = event.nutritionValues.calories!=0
                )
            }
            is LoadingEvent.ReceivedInformation -> {
                _loadingState.value = loadingState.value.copy(
                    hasInformation = event.userInformation.age!=0
                )
            }
        }
    }

    fun checkUser(){
        _loadingState.value = loadingState.value.copy(
            isLoggedIn = firebaseAuth.currentUser!=null
        )
    }
}