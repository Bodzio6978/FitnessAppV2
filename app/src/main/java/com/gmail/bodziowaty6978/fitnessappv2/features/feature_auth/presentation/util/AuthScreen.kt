package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util

sealed class AuthScreen(val route:String) {
    object LoginAuthScreen: AuthScreen("login_screen")
    object RegisterAuthScreen: AuthScreen("register_screen")
    object ResetPasswordAuthScreen: AuthScreen("reset_password_screen")
}