package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.model.NavigationAction
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.AuthUseCases
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthEvent
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.TextFieldState
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.navigation.AuthNavigationActions
import com.google.rpc.context.AttributeContext.Auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val authUseCases: AuthUseCases,
    private val navigator:Navigator
): ViewModel(){

    private val _emailState = mutableStateOf<TextFieldState>(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    private val _passwordState = mutableStateOf<TextFieldState>(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: State<Boolean> = _isLoading

    private val _snackbarState = MutableSharedFlow<String>()
    val snackbarState: SharedFlow<String> = _snackbarState

    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EnteredEmail -> {
                _emailState.value = emailState.value.copy(
                    text = event.email,
                )
                _emailState.value = emailState.value.copy(
                    isHintVisible = _emailState.value.text.isBlank()
                )
            }
            is AuthEvent.EnteredPassword -> {
                _passwordState.value = passwordState.value.copy(
                    text = event.password,
                )
                _passwordState.value = passwordState.value.copy(
                    isHintVisible = _passwordState.value.text.isBlank()
                )


            }
            is AuthEvent.RegisterLoginButtonClicked -> {
                navigator.navigate(AuthNavigationActions.LoginScreen.loginToRegister())
            }
            is AuthEvent.ForgotButtonClicked -> {
                navigator.navigate(AuthNavigationActions.LoginScreen.loginToReset())
            }
            else -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _isLoading.value = true
                    val result = authUseCases.logInUser(
                        email = _emailState.value.text,
                        password = _passwordState.value.text
                    )
                    if (result is Result.Error){
                        _snackbarState.emit(result.message)
                    }else{
                        navigator.navigate(AuthNavigationActions.LoginScreen.loginToSummary())
                    }
                    _isLoading.value = false
                }
            }
        }
    }

}