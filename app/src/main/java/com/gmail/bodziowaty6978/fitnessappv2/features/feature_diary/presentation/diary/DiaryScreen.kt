package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components.CalendarSection
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components.DiaryMealSection
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components.NutritionBottomSection
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components.NutritionTopSection
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Grey

@Composable
fun DiaryScreen(
    navController: NavController,
    viewModel: DiaryViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        viewModel.getDiaryEntries()
    }

    val meals = viewModel.mealsState.value

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CalendarSection(
                onArrowPressed = {
                    viewModel.onEvent(
                        event = DiaryEvent.ChangedDate
                    )
                }
            )
            NutritionTopSection()

            meals.forEach { meal ->
                DiaryMealSection(meal = meal)
            }

        }
        
        NutritionBottomSection(meals = meals,
            wantedNutritionValues = NutritionValues(
            calories = 3384,
            protein = 253.0,
            carbohydrates = 338.4,
            fat = 112.8),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Grey)
        )
    }

}