package com.gmail.bodziowaty6978.fitnessappv2

import android.app.Application
import android.content.Context
import androidx.datastore.dataStore
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.util.NutritionValuesSerializer
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.util.UserInformationSerializer
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Constants
import dagger.hilt.android.HiltAndroidApp

val Context.datastoreInformation by dataStore(Constants.DATASTORE_INFORMATION, UserInformationSerializer)
val Context.datastoreNutrition by dataStore(Constants.DATASTORE_NUTRITION, NutritionValuesSerializer)

@HiltAndroidApp
class FitnessApp:Application() {
}