package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.Toolbar
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.components.TextField
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthEvent
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthScreen
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.BottomBarScreen
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val isLoadingState = viewModel.isLoading.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value

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
            Toolbar(
                title = stringResource(id = R.string.login),
                isBackArrowVisible = false,
                ){

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
                    )

                    Button(
                        onClick = {
                            viewModel.onEvent(AuthEvent.AuthButtonClicked)
                        },
                        modifier = Modifier
                            .padding(top = 20.dp, end = 20.dp, start = 20.dp)
                            .fillMaxWidth()
                    ) {

                        Text(
                            text = stringResource(id = R.string.sign_in).uppercase(),
                            style = MaterialTheme.typography.button,
                            modifier = Modifier
                                .padding(5.dp),
                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp)
                    ) {

                        Text(
                            text = stringResource(id = R.string.forgot_password),
                            color = Color.LightGray,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .clickable {
                                    viewModel.onEvent(AuthEvent.ForgotButtonClicked)
                                }
                                .padding(10.dp)
                        )


                    }
                }

//                GoogleSignInButton()

                Text(
                    text = stringResource(id = R.string.i_don_t_have_an_account_register),
                    style = MaterialTheme.typography.body2,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .clickable {
                            viewModel.onEvent(AuthEvent.RegisterLoginButtonClicked)
                        }
                        .padding(10.dp)
                        .align(Alignment.BottomCenter)
                )

            } else {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}