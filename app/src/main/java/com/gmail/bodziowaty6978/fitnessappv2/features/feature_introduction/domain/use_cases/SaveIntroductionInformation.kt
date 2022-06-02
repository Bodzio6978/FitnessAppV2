package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.repository.IntroductionRepository
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.util.IntroductionExpectedQuestionAnswer
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result

class SaveIntroductionInformation(
    private val introductionRepository: IntroductionRepository,
    private val resourceProvider: ResourceProvider,
    private val calculateNutritionValues: CalculateNutritionValues
) {

    suspend operator fun invoke(
        answers:Map<IntroductionExpectedQuestionAnswer,String>
    ): Result {
        var result: Result = Result.Success

        val gender = answers[IntroductionExpectedQuestionAnswer.Gender]!!.toInt()
        val currentWeight = answers[IntroductionExpectedQuestionAnswer.CurrentWeight]!!.replace(",",".").toDoubleOrNull()
        val height = answers[IntroductionExpectedQuestionAnswer.Height]!!.replace(",",".").toDoubleOrNull()
        val workType = answers[IntroductionExpectedQuestionAnswer.TypeOfWork]!!.toInt()
        val workoutsInWeek = answers[IntroductionExpectedQuestionAnswer.HowOftenDoYouTrain]!!.toInt()
        val activityInDay = answers[IntroductionExpectedQuestionAnswer.ActivityDuringADay]!!.toInt()
        val wantedWeight = answers[IntroductionExpectedQuestionAnswer.DesiredWeight]!!.replace(",",".").toDoubleOrNull()
        val age = answers[IntroductionExpectedQuestionAnswer.Age]!!.replace(",",".").toIntOrNull()

        if (currentWeight==null||currentWeight<=0){
            result = Result.Error(
                message = resourceProvider.getString(R.string.please_make_sure_you_have_entered_your_weight)
            )
        }
        if (wantedWeight==null||wantedWeight<=0){
            result = Result.Error(
                message = resourceProvider.getString(R.string.please_make_sure_you_have_entered_your_desired_weight)
            )
        }
        if (height==null||height<=0){
            result = Result.Error(
                message = resourceProvider.getString(R.string.please_make_sure_you_have_entered_your_age)
            )
        }
        if (age==null||age<=0){
            result = Result.Error(
                message = resourceProvider.getString(R.string.please_make_sure_you_have_entered_your_age)
            )
        }
        if (result !is Result.Error){
            val nutritionValues = calculateNutritionValues(
                gender ,
                currentWeight!!,
                height!!,
                workType,
                workoutsInWeek,
                activityInDay,
                wantedWeight!!,
                age!!
            )
            result = introductionRepository.saveIntroductionInformation(
                userInformation = UserInformation(
                    activityInADay = activityInDay,
                    typeOfWork = workType,
                    workoutInAWeek = workoutsInWeek,
                    gender = gender,
                    height = height,
                    age = age,
                    wantedWeight = wantedWeight,
                    currentWeight = currentWeight,
                ),
                nutritionValues = nutritionValues
            )
        }

        return result
    }


}