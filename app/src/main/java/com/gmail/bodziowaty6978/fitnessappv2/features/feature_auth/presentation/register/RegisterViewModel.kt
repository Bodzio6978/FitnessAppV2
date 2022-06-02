package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.AuthUseCases
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthEvent
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.TextFieldState
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val navigator: Navigator
): ViewModel(){

    private val _emailState = mutableStateOf<TextFieldState>(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    private val _passwordState = mutableStateOf<TextFieldState>(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    private val _confirmPasswordState = mutableStateOf<TextFieldState>(TextFieldState())
    val confirmPasswordState: State<TextFieldState> = _confirmPasswordState

    private val _usernameState = mutableStateOf<TextFieldState>(TextFieldState())
    val usernameState: State<TextFieldState> = _usernameState

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
            is AuthEvent.EnteredUsername -> {
                _usernameState.value = usernameState.value.copy(
                    text = event.username,
                )
                _confirmPasswordState.value = usernameState.value.copy(
                    isHintVisible = _usernameState.value.text.isBlank()
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
            is AuthEvent.EnteredConfirmPassword -> {
                _confirmPasswordState.value = confirmPasswordState.value.copy(
                    text = event.confirmPassword,
                )
                _confirmPasswordState.value = confirmPasswordState.value.copy(
                    isHintVisible = _confirmPasswordState.value.text.isBlank()
                )
            }
            is AuthEvent.AuthButtonClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _isLoading.value = true
                    val result = authUseCases.registerUser(
                        email = _emailState.value.text,
                        password = _passwordState.value.text,
                        confirmPassword = _confirmPasswordState.value.text,
                        username = _usernameState.value.text
                    )
                    if (result is Result.Error){
                        _snackbarState.emit(result.message)
                    }else{
                        navigator.navigate(NavigationActions.RegisterScreen.registerToSummary())
                    }

                    _isLoading.value = false
                }
            }
            else -> {
                navigator.navigate(NavigationActions.RegisterScreen.registerToLogin())
            }
        }
    }

}