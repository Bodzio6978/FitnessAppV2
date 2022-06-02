package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.di

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.data.repository.AuthRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.AuthUseCases
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.LogInUser
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.RegisterUser
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.ResetPasswordWithEmail
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mockito.Mockito
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = object :AuthRepository{
        override suspend fun logInUser(email: String, password: String): Result {
            return Result.Success
        }

        override suspend fun registerUser(email: String, password: String): Result {
            return Result.Success
        }

        override suspend fun sendPasswordResetEmail(email: String): Result {
            return Result.Success
        }
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(authRepository: AuthRepository, resourceProvider: ResourceProvider): AuthUseCases = AuthUseCases(
        logInUser = LogInUser(
            repository = authRepository,
            resourceProvider = resourceProvider
        ),

        registerUser = RegisterUser(
            repository = authRepository,
            resourceProvider = resourceProvider
        ),

        resetPasswordWithEmail = ResetPasswordWithEmail(
            repository = authRepository,
            resourceProvider = resourceProvider
        ),

        )
}