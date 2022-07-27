package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.register

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val isLoadingState = viewModel.isLoading.value
    val usernameState = viewModel.usernameState.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val confirmPasswordState = viewModel.confirmPasswordState.value


    Scaffold(
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


                    DefaultTextField(
                        value = emailState.text,
                        placeholder = {
                            Text(text = emailState.hint)
                        },
                        onValueChange = {
                            viewModel.onEvent(
                                AuthEvent.EnteredEmail(email = it)
                            )
                        },
                        keyboardOptions = KeyboardOptions().copy(
                            keyboardType = KeyboardType.Email
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.EMAIL))
                            .padding(horizontal = 20.dp),
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Email, contentDescription = passwordState.hint)
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    DefaultTextField(
                        value = usernameState.text,
                        placeholder = {
                            Text(text = usernameState.hint)
                        },
                        onValueChange = {
                            viewModel.onEvent(
                                AuthEvent.EnteredUsername(username = it)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.USERNAME))
                            .padding(horizontal = 20.dp),
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = passwordState.hint)
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

                    Spacer(modifier = Modifier.height(15.dp))

                    DefaultTextField(
                        value = confirmPasswordState.text,
                        placeholder = {
                            Text(text = confirmPasswordState.hint)
                        },
                        onValueChange = {
                            viewModel.onEvent(
                                AuthEvent.EnteredConfirmPassword(confirmPassword = it)
                            )
                        },
                        keyboardOptions = KeyboardOptions().copy(
                            keyboardType = KeyboardType.Password,
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.CONFIRM))
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
                            .padding(top = 20.dp, end = 20.dp, start = 20.dp, bottom = 100.dp)
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.BUTTON)),
                        shape = RoundedCornerShape(50)
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
