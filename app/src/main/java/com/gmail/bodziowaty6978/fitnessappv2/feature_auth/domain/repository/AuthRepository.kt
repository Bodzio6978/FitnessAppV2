package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository

import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult

interface AuthRepository {

    suspend fun logInUser(
        email: String,
        password: String
    ): CustomResult

    suspend fun registerUser(
        email: String,
        password: String
    ): CustomResult

    suspend fun sendPasswordResetEmail(
        email: String
    ): CustomResult
}