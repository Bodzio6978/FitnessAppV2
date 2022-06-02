package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.di

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.data.repository.remote.DiaryRepositoryImp
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.repository.DiaryRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases.GetDiaryEntries
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.use_cases.SortDiaryEntries
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiaryModule {

    @Singleton
    @Provides
    fun provideDiaryRepository(
        firebaseFirestore: FirebaseFirestore,
        userId:String?
    ):DiaryRepository = DiaryRepositoryImp(
        firebaseFirestore = firebaseFirestore,
        userId = userId
    )

    @Singleton
    @Provides
    fun provideGetDiaryEntriesUseCase(
        diaryRepository: DiaryRepository
    ):GetDiaryEntries = GetDiaryEntries(
        diaryRepository = diaryRepository,
        sortDiaryEntries = SortDiaryEntries()
    )

}