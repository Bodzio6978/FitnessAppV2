package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case

data class AuthUseCases(
    val logInUser: LogInUser,
    val registerUser: RegisterUser,
    val resetPasswordWithEmail: ResetPasswordWithEmail
)