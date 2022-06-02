package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util

sealed class AuthEvent {
    data class EnteredEmail(val email:String): AuthEvent()
    data class EnteredPassword(val password:String): AuthEvent()
    data class EnteredUsername(val username:String): AuthEvent()
    data class EnteredConfirmPassword(val confirmPassword:String): AuthEvent()
    object AuthButtonClicked: AuthEvent()
    object RegisterLoginButtonClicked:AuthEvent()
    object ForgotButtonClicked:AuthEvent()
    object BackArrowPressed:AuthEvent()
}