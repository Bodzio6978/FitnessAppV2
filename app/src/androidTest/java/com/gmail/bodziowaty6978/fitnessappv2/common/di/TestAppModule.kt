package com.gmail.bodziowaty6978.fitnessappv2.common.di

import android.app.Application
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.AuthUseCases
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.LogInUser
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.RegisterUser
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.ResetPasswordWithEmail
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.data.repository.remote.FakeDiaryRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.*
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.repository.IntroductionRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.use_cases.CalculateNutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.use_cases.SaveIntroductionInformation
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    fun provideNavigator():Navigator = ComposeCustomNavigator()

    @Provides
    @Singleton
    fun provideResourceProvider(app: Application): ResourceProvider = ResourceProvider(app)

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = object : AuthRepository {
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

    @Provides
    @Singleton
    fun provideIntroductionRepository(): IntroductionRepository = object : IntroductionRepository {
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

    @Singleton
    @Provides
    fun provideDiaryRepository(): DiaryRepository = FakeDiaryRepository()

    @Singleton
    @Provides
    fun provideGetDiaryEntriesUseCase(
        diaryRepository: DiaryRepository
    ): GetDiaryEntries = GetDiaryEntries(
        diaryRepository = diaryRepository,
        sortDiaryEntries = SortDiaryEntries()
    )

    @Singleton
    @Provides
    fun provideSearchForProductsUseCase(diaryRepository: DiaryRepository) : SearchForProducts = SearchForProducts(diaryRepository)

    @Singleton
    @Provides
    fun provideGetDiaryHistoryUseCase(diaryRepository: DiaryRepository) : GetDiaryHistory = GetDiaryHistory(diaryRepository)

    @Singleton
    @Provides
    fun provideDiarySearchUseCases(diaryRepository: DiaryRepository): SearchDiaryUseCases =
        SearchDiaryUseCases(
            searchForProducts = SearchForProducts(diaryRepository),
            getDiaryHistory = GetDiaryHistory(diaryRepository)
        )
}