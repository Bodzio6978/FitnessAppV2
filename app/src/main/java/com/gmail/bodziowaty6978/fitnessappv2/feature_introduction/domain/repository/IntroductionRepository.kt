package com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.repository

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult

interface IntroductionRepository {

    suspend fun saveIntroductionInformation(
        userInformation: UserInformation,
        nutritionValues: NutritionValues
    ): CustomResult

}