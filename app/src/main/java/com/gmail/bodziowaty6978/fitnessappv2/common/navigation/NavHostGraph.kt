package com.gmail.bodziowaty6978.fitnessappv2.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.BottomBarScreen
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.Screen
import com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions.asLifecycleAwareState
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_account.presentation.AccountScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.login.LoginScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.register.RegisterScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.reset_password.ResetPasswordScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.DiaryScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.IntroductionScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_splash.loading.presentation.SplashScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_summary.presentation.SummaryScreen

@Composable
fun NavHostGraph(
    navController: NavHostController = rememberNavController(),
    navigator: Navigator,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigatorState by navigator.navActions.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = null
    )

    LaunchedEffect(navigatorState) {
        navigatorState?.let {
            it.parcelableArguments.forEach { argument ->
                navController.currentBackStackEntry?.arguments?.putParcelable(
                    argument.key,
                    argument.value
                )
            }
            navController.navigate(it.destination, it.navOptions)
        }
    }

    NavHost(
        navController = navController,
        startDestination = AuthScreen.LoginAuthScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(navHostController = navController)
        }

        composable(
            route = Screen.IntroductionScreen.route
        ) {
            IntroductionScreen(navController = navController)
        }

        composable(
            route = AuthScreen.LoginAuthScreen.route
        ) {
            LoginScreen()
        }
        composable(
            route = AuthScreen.RegisterAuthScreen.route
        ) {
            RegisterScreen()
        }
        composable(
            route = AuthScreen.ResetPasswordAuthScreen.route
        ) {
            ResetPasswordScreen()
        }
        composable(
            route = BottomBarScreen.Summary.route
        ) {
            SummaryScreen(
                navController = navController
            )
        }

        composable(
            route = BottomBarScreen.Diary.route
        ) {
            DiaryScreen(
                navController = navController
            )
        }

        composable(
            route = BottomBarScreen.Account.route
        ) {
            AccountScreen(
                navController = navController
            )
        }
    }

}