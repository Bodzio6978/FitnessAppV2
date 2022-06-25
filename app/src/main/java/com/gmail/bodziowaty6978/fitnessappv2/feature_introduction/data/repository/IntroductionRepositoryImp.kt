package com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.data.repository

import androidx.datastore.core.DataStore
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.repository.IntroductionRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await


class IntroductionRepositoryImp(
    private val firestore: FirebaseFirestore,
    private val userId:String?,
    private val informationDatastore : DataStore<UserInformation>,
    private val nutritionDatastore : DataStore<NutritionValues>
): IntroductionRepository {

    override suspend fun saveIntroductionInformation(
        userInformation: UserInformation,
        nutritionValues: NutritionValues
    ): Result {
        return try {
            val userRef = firestore.collection("users").document(userId!!)
            val batch = firestore.batch()
            batch.set(userRef,userInformation, SetOptions.merge())
            batch.set(userRef,nutritionValues, SetOptions.merge())
            batch.commit().await()

            informationDatastore.updateData {
                it.copy(
                    activityInADay = userInformation.activityInADay,
                    typeOfWork = userInformation.typeOfWork,
                    workoutInAWeek = userInformation.workoutInAWeek,
                    gender = userInformation.gender,
                    height = userInformation.height,
                    currentWeight = userInformation.currentWeight,
                    wantedWeight = userInformation.wantedWeight,
                    age = userInformation.age,
                )
            }

            nutritionDatastore.updateData {
                it.copy(
                    calories = nutritionValues.calories,
                    carbohydrates = nutritionValues.carbohydrates,
                    protein = nutritionValues.protein,
                    fat = nutritionValues.fat,
                )
            }

            Result.Success
        }catch (e:Exception){
            Result.Error(e.message.toString())
        }

    }
}