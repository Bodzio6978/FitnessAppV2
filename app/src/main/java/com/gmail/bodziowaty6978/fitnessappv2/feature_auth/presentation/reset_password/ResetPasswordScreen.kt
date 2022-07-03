package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.reset_password

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.DefaultTextField
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.Toolbar
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.util.AuthEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ResetPasswordScreen(
    viewModel: ResetPasswordViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val isLoadingState = viewModel.isLoading.value
    val emailState = viewModel.emailState.value

    LaunchedEffect(key1 = true) {
        viewModel.uiState.collectLatest {
            when (it) {
                is CustomResult.Success -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Successfully sent an email"
                    )
                }
                is CustomResult.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.reset_your_password),
                isBackArrowVisible = true,
            ){
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
                            text = stringResource(id = R.string.reset_my_password).uppercase(),
                            style = MaterialTheme.typography.button,
                            modifier = Modifier
                                .padding(5.dp),
                        )

                    }
                }

            } else {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}