package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.DefaultTextField
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.Toolbar
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.util.AuthEvent
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG
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
            ) {

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

                    DefaultTextField(
                        value = emailState.text,
                        placeholder = {
                            Text(text = emailState.hint)
                        },
                        onValueChange = {
                            Log.e("huj2",it)
                            viewModel.onEvent(AuthEvent.EnteredEmail(email = it))
                        },
                        keyboardOptions = KeyboardOptions().copy(
                            keyboardType = KeyboardType.Email
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.EMAIL))
                            .padding(horizontal = 20.dp),
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Email, contentDescription = emailState.hint)
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                    DefaultTextField(
                        value = passwordState.text,
                        placeholder = {
                            Text(text = passwordState.hint)
                        },
                        onValueChange = {
                            viewModel.onEvent(
                                AuthEvent.EnteredPassword(password = it)
                            )
                            Log.e(TAG,it)
                        },
                        keyboardOptions = KeyboardOptions().copy(
                            keyboardType = KeyboardType.Password,
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.PASSWORD))
                            .padding(horizontal = 20.dp),
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Lock, contentDescription = passwordState.hint)
                        }
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = {
                            viewModel.onEvent(AuthEvent.AuthButtonClicked)
                        },
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.BUTTON)),
                        shape = RoundedCornerShape(50)
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