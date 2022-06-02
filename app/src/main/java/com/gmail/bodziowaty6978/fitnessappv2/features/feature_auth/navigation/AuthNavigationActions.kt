package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.navigation

import androidx.navigation.NavOptions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.model.NavigationAction
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.BottomBarScreen
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.AuthScreen

object AuthNavigationActions {
    object LoginScreen{
        fun loginToRegister() = object : NavigationAction {
            override val destination: String
                get() = AuthScreen.RegisterAuthScreen.route
        }
        fun loginToReset() = object : NavigationAction {
            override val destination: String
                get() = AuthScreen.ResetPasswordAuthScreen.route
        }
        fun loginToSummary() = object : NavigationAction {
            override val destination: String
                get() = BottomBarScreen.Summary.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }
    }
    object RegisterScreen{
        fun registerToLogin() = object : NavigationAction {
            override val destination: String
                get() = AuthScreen.LoginAuthScreen.route
        }
        fun registerToSummary() = object : NavigationAction {
            override val destination: String
                get() = BottomBarScreen.Summary.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }

    }
    object ResetScreen{
        fun resetToLogin() = object : NavigationAction {
            override val destination: String
                get() = AuthScreen.LoginAuthScreen.route
        }
    }
}