package com.gmail.bodziowaty6978.fitnessappv2.feature_splash.loading.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.datastoreInformation
import com.gmail.bodziowaty6978.fitnessappv2.datastoreNutrition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    viewModel: LoadingViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        context.datastoreInformation.data.collect {
            launch(Dispatchers.Main) {
                viewModel.onEvent(
                    LoadingEvent.ReceivedInformation(it)
                )
            }

        }
    }

    LaunchedEffect(key1 = true) {
        context.datastoreNutrition.data.collect {
            launch(Dispatchers.Main) {
                viewModel.onEvent(
                    LoadingEvent.ReceivedNutrition(it)
                )
            }

        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.checkIfUserLoggedIn()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h1
            )
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 50.dp)
            )
        }
    }
}