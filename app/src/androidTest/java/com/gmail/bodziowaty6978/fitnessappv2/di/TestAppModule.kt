package com.gmail.bodziowaty6978.fitnessappv2.di

import android.app.Application
import android.content.Context
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.datastore.core.DataStore
import com.gmail.bodziowaty6978.fitnessappv2.datastoreInformation
import com.gmail.bodziowaty6978.fitnessappv2.datastoreNutrition
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.data.repository.AuthRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.AuthUseCases
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.LogInUser
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.RegisterUser
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case.ResetPasswordWithEmail
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.data.repository.remote.DiaryRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.data.repository.remote.FakeDiaryRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntryWithId
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository.DiaryRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases.GetDiaryEntries
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases.SearchForProducts
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases.SortDiaryEntries
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.data.repository.IntroductionRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.repository.IntroductionRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.use_cases.CalculateNutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.use_cases.SaveIntroductionInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mockito.Mockito
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
    fun provideAuthRepository(): AuthRepository = object : AuthRepository{
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
    fun provideIntroductionRepository(): IntroductionRepository = object : IntroductionRepository{
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
}