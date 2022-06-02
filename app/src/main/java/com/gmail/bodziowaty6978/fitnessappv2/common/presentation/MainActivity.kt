package com.gmail.bodziowaty6978.fitnessappv2.common.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavHostGraph
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_account.presentation.AccountScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.login.LoginScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.register.RegisterScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.reset_password.ResetPasswordScreen
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.FitnessAppV2Theme
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.DiaryScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.IntroductionScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_splash.loading.presentation.SplashScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_summary.presentation.SummaryScreen
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.BottomBar
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.BottomBarScreen
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.Screen
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator:Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            FitnessAppV2Theme {
                NavHostGraph(navigator = navigator)
            }
        }
    }
}

