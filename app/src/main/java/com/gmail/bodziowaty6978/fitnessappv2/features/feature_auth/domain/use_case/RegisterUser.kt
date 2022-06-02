package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case

import android.util.Patterns
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider

class RegisterUser(
    private val repository: AuthRepository,
    private val resourceProvider: ResourceProvider
){

    suspend operator fun invoke(email:String,password:String,confirmPassword:String,username:String): Result {
        if (email.isBlank()||password.isBlank()||username.isBlank()||confirmPassword.isBlank()){
            return Result.Error(resourceProvider.getString(R.string.please_make_sure_all_fields_are_filled_in_correctly))
        }
        if (confirmPassword!=password){
            return Result.Error(resourceProvider.getString(R.string.please_make_sure_both_passwords_are_the_same))
        }
        if (password.length<6||password.length>24){
            return Result.Error(resourceProvider.getString(R.string.please_make_sure_your_password_is_at_least_6_characters_and_is_not_longer_than_24_characters))
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return Result.Error(resourceProvider.getString(R.string.please_make_sure_you_have_entered_correct_email))
        }
        return repository.registerUser(
            email = email,
            password = password
        )
    }
}