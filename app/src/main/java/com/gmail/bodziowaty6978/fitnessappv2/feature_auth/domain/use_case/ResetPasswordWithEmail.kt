package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case

import android.util.Patterns
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository.AuthRepository

class ResetPasswordWithEmail(
    private val repository: AuthRepository,
    private val resourceProvider: ResourceProvider
){

    suspend operator fun invoke(email: String): CustomResult {
        if (email.isBlank()){
            return CustomResult.Error(resourceProvider.getString(R.string.please_enter_your_email_in_order_to_reset_your_password))
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return CustomResult.Error(resourceProvider.getString(R.string.please_make_sure_you_have_entered_correct_email))
        }
        return repository.sendPasswordResetEmail(email)
    }
}