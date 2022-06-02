package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.di

import androidx.datastore.core.DataStore
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.data.repository.IntroductionRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.repository.IntroductionRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.use_cases.CalculateNutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.use_cases.SaveIntroductionInformation
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestIntroductionModule {

    @Provides
    @Singleton
    fun provideIntroductionRepository(): IntroductionRepository = object :IntroductionRepository{
        override suspend fun saveIntroductionInformation(
            userInformation: UserInformation,
            nutritionValues: NutritionValues
        ): Result {
            return Result.Success
        }
    }

    @Provides
    @Singleton
    fun provideCalculateNutritionValuesUseCase(): CalculateNutritionValues = CalculateNutritionValues()

    @Provides
    @Singleton
    fun provideSaveInformationUseCase(
        introductionRepository: IntroductionRepository,
        resourceProvider: ResourceProvider,
        calculateNutritionValues: CalculateNutritionValues
    ): SaveIntroductionInformation = SaveIntroductionInformation(
        introductionRepository = introductionRepository,
        resourceProvider = resourceProvider,
        calculateNutritionValues = calculateNutritionValues
    )

}