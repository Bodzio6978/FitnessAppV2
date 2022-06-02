package com.gmail.bodziowaty6978.fitnessappv2.di

import android.app.Application
import android.content.Context
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.datastore.core.DataStore
import com.gmail.bodziowaty6978.fitnessappv2.datastoreInformation
import com.gmail.bodziowaty6978.fitnessappv2.datastoreNutrition
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
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

    @Provides
    @Singleton
    fun provideResourceProvider(app: Application): ResourceProvider = ResourceProvider(app)

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        val firebaseInstance = FirebaseFirestore.getInstance()
        firebaseInstance.disableNetwork()
        return firebaseInstance
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
    ): DataStore<NutritionValues> = context.datastoreNutrition

    @Singleton
    @Provides
    fun provideUserInformationDatastore(
        @ApplicationContext context: Context
    ): DataStore<UserInformation> = context.datastoreInformation

}