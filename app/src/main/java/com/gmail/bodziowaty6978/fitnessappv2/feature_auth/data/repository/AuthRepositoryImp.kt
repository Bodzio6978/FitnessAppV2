package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.data.repository

import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun logInUser(email: String, password: String): CustomResult {
        return try {
            firebaseAuth.signInWithEmailAndPassword(
                email,
                password
            ).await()
            CustomResult.Success
        } catch (e: Exception) {
            CustomResult.Error(e.message.toString())
        }
    }

    override suspend fun registerUser(
        email: String,
        password: String,
    ): CustomResult {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(
                email,
                password
            ).await()
            CustomResult.Success
        } catch (e: Exception) {
            CustomResult.Error(e.message.toString())
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): CustomResult {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            CustomResult.Success
        } catch (e: Exception) {
            CustomResult.Error(e.message.toString())
        }
    }
}