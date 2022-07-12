package com.gmail.bodziowaty6978.fitnessappv2.common.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.room.Room
import com.gmail.bodziowaty6978.fitnessappv2.common.data.room.AppRoomDatabase
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.datastoreInformation
import com.gmail.bodziowaty6978.fitnessappv2.datastoreNutrition
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.data.repository.AuthRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.AuthUseCases
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.LogInUser
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.RegisterUser
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.ResetPasswordWithEmail
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.data.repository.remote.DiaryRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.repository.DiaryRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.*
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product.AddDiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product.CreatePieChartData
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product.ProductUseCases
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product.SaveProductToHistory
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.data.repository.IntroductionRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.repository.IntroductionRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.use_cases.CalculateNutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.use_cases.SaveIntroductionInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ):AppRoomDatabase = Room.databaseBuilder(
        context,
        AppRoomDatabase::class.java,
        AppRoomDatabase.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideNavigator():Navigator = ComposeCustomNavigator()

    @Provides
    @Singleton
    fun provideResourceProvider(app: Application): ResourceProvider = ResourceProvider(app)

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideUserId(firebaseAuth: FirebaseAuth): String? {
        return firebaseAuth.currentUser?.uid
    }


    @Singleton
    @Provides
    fun provideNutritionDatastore(
        @ApplicationContext context: Context
    ):DataStore<NutritionValues> = context.datastoreNutrition

    @Singleton
    @Provides
    fun provideUserInformationDatastore(
        @ApplicationContext context: Context
    ):DataStore<UserInformation> = context.datastoreInformation

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepositoryImp(firebaseAuth)

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
    fun provideIntroductionRepository(
        firebaseFirestore: FirebaseFirestore,
        userId:String?,
        informationDatastore: DataStore<UserInformation>,
        nutritionDatastore: DataStore<NutritionValues>,
    ): IntroductionRepository = IntroductionRepositoryImp(
        firestore = firebaseFirestore,
        userId = userId,
        informationDatastore = informationDatastore,
        nutritionDatastore = nutritionDatastore
    )

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
    fun provideDiaryRepository(
        firebaseFirestore: FirebaseFirestore,
        userId:String?,
        roomDatabase: AppRoomDatabase,
        resourceProvider: ResourceProvider
    ): DiaryRepository =
        DiaryRepositoryImp(
            firebaseFirestore = firebaseFirestore,
            userId = userId,
            productDao = roomDatabase.productDao(),
            resourceProvider = resourceProvider
        )

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
    fun provideSearchForProductsUseCase(
        diaryRepository: DiaryRepository,
        resourceProvider: ResourceProvider,
        getDiaryHistory: GetDiaryHistory
    ) : SearchForProducts = SearchForProducts(
        diaryRepository = diaryRepository,
        resourceProvider = resourceProvider,
        getDiaryHistory = getDiaryHistory
    )

    @Singleton
    @Provides
    fun provideGetDiaryHistoryUseCase(diaryRepository: DiaryRepository) : GetDiaryHistory = GetDiaryHistory(diaryRepository)

    @Singleton
    @Provides
    fun provideDiarySearchUseCases(diaryRepository: DiaryRepository, searchForProducts: SearchForProducts): SearchDiaryUseCases =
        SearchDiaryUseCases(
            searchForProducts = searchForProducts,
            getDiaryHistory = GetDiaryHistory(diaryRepository)
        )

    @Singleton
    @Provides
    fun provideProductUseCases(
        diaryRepository: DiaryRepository,
         resourceProvider: ResourceProvider
    ): ProductUseCases =
        ProductUseCases(
            calculateNutritionValues = com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product.CalculateNutritionValues(),
            createPieChartData = CreatePieChartData(),
            addDiaryEntry = AddDiaryEntry(diaryRepository, resourceProvider = resourceProvider),
            saveProductToHistory = SaveProductToHistory(diaryRepository)

        )

}