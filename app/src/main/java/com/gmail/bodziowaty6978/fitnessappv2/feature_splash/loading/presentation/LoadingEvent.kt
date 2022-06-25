package com.gmail.bodziowaty6978.fitnessappv2.feature_splash.loading.presentation

import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation

sealed class LoadingEvent {
    data class ReceivedNutrition(val nutritionValues: NutritionValues): LoadingEvent()
    data class ReceivedInformation(val userInformation: UserInformation): LoadingEvent()
}