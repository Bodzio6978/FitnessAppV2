package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.reset_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.TextFieldState
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.AuthUseCases
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.util.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val navigator:Navigator,
    private val resourceProvider: ResourceProvider
): ViewModel(){

    private val _emailState = mutableStateOf<TextFieldState>(TextFieldState(
        hint = resourceProvider.getString(R.string.email_address)
    ))
    val emailState: State<TextFieldState> = _emailState

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: State<Boolean> = _isLoading

    private val _uiState = MutableSharedFlow<CustomResult>()
    val uiState: SharedFlow<CustomResult> = _uiState

    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EnteredEmail -> {
                _emailState.value = emailState.value.copy(
                    text = event.email,
                )
            }
            is AuthEvent.RegisterLoginButtonClicked -> {
                navigator.navigate(NavigationActions.ResetScreen.resetToLogin())
            }
            else -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _isLoading.value = true
                    val result = authUseCases.resetPasswordWithEmail(
                        email = _emailState.value.text
                    )
                    _uiState.emit(result)
                    _isLoading.value = false
                }
            }
        }
    }

}