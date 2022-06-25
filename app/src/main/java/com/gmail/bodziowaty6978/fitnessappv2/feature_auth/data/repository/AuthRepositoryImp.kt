package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.data.repository

import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun logInUser(email: String, password: String): Result {
        return try {
            firebaseAuth.signInWithEmailAndPassword(
                email,
                password
            ).await()
            Result.Success
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun registerUser(
        email: String,
        password: String,
    ): Result {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(
                email,
                password
            ).await()
            Result.Success
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Result {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.Success
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}