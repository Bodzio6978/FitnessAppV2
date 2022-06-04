package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.Toolbar
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.components.TextField
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthEvent
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.Screen
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val isLoadingState = viewModel.isLoading.value
    val usernameState = viewModel.usernameState.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val confirmPasswordState = viewModel.confirmPasswordState.value

    LaunchedEffect(key1 = true) {
        viewModel.snackbarState.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(
                message = it
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Toolbar(title = stringResource(id = R.string.register), isBackArrowVisible = true) {
                viewModel.onEvent(AuthEvent.RegisterLoginButtonClicked)
            }
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (!isLoadingState) {

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {


                    TextField(
                        value = emailState.text,
                        hint = stringResource(id = R.string.email_address),
                        onValueChange = {
                            viewModel.onEvent(
                                AuthEvent.EnteredEmail(email = it)
                            )
                        },
                        isHintVisible = emailState.isHintVisible,
                        textStyle = MaterialTheme.typography.body1,
                        keyboardType = KeyboardType.Email,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.EMAIL))
                    )

                    TextField(
                        value = usernameState.text,
                        hint = stringResource(id = R.string.username),
                        onValueChange = {
                            viewModel.onEvent(
                                AuthEvent.EnteredUsername(username = it)
                            )
                        },
                        isHintVisible = usernameState.isHintVisible,
                        textStyle = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.USERNAME))
                    )

                    TextField(
                        value = passwordState.text,
                        hint = stringResource(id = R.string.password),
                        onValueChange = {
                            viewModel.onEvent(
                                AuthEvent.EnteredPassword(password = it)
                            )
                        },
                        isHintVisible = passwordState.isHintVisible,
                        textStyle = MaterialTheme.typography.body1,
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.PASSWORD))
                    )

                    TextField(
                        value = confirmPasswordState.text,
                        hint = stringResource(id = R.string.confirm_your_password),
                        onValueChange = {
                            viewModel.onEvent(
                                AuthEvent.EnteredConfirmPassword(confirmPassword = it)
                            )
                        },
                        isHintVisible = confirmPasswordState.isHintVisible,
                        textStyle = MaterialTheme.typography.body1,
                        keyboardType = KeyboardType.Password,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.CONFIRM))
                    )


                    Button(
                        onClick = {
                            viewModel.onEvent(AuthEvent.AuthButtonClicked)
                        },
                        modifier = Modifier
                            .padding(top = 20.dp, end = 20.dp, start = 20.dp)
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.BUTTON))
                    ) {

                        Text(
                            text = stringResource(id = R.string.sign_up).uppercase(),
                            style = MaterialTheme.typography.button,
                            modifier = Modifier
                                .padding(5.dp),
                        )

                    }
                }

                Text(
                    text = stringResource(id = R.string.i_have_an_account_login),
                    style = MaterialTheme.typography.body2,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .clickable {
                            viewModel.onEvent(AuthEvent.RegisterLoginButtonClicked)
                        }
                        .align(Alignment.BottomCenter)
                        .padding(10.dp)


                )

            } else {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
