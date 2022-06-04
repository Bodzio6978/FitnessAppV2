package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.reset_password

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.Toolbar
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.components.TextField
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthEvent
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
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
                is Result.Success -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Successfully sent an email"
                    )
                }
                is Result.Error -> {
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