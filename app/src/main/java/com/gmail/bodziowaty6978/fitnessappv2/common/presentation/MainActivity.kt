package com.gmail.bodziowaty6978.fitnessappv2.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.navigation.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.snackbar.SnackbarTextHolder
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.navigation.NavHostGraph
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.FitnessAppV2Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var snackbarTextHolder: SnackbarTextHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitnessAppV2Theme {
                NavHostGraph(
                    navigator = navigator,
                    snackbarTextHolder = snackbarTextHolder
                )
            }
        }
    }
}

