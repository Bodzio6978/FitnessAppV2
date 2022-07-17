package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Grey
import com.gmail.bodziowaty6978.fitnessappv2.datastoreNutrition
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components.CalendarSection
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components.DiaryMealSection
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components.NutritionBottomSection

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    val context = LocalContext.current

    var nutritionValues by remember{
        mutableStateOf(NutritionValues())
    }

    LaunchedEffect(key1 = true){
        viewModel.getDiaryEntries()
    }

    LaunchedEffect(key1 = true){
        context.datastoreNutrition.data.collect{
            nutritionValues = it
        }
    }

    val meals = viewModel.mealsState.value

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CalendarSection(
                onArrowPressed = {
                    viewModel.onEvent(
                        event = DiaryEvent.ChangedDate
                    )
                }
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f, fill = false)
            ) {
                meals.forEach { meal ->
                    DiaryMealSection(
                        meal = meal,
                        onAddProductClick = {
                            viewModel.onEvent(DiaryEvent.ClickedAddProduct(mealName = it))
                        }
                    )
                }
            }
        }
        NutritionBottomSection(meals = meals,
            wantedNutritionValues = nutritionValues,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Grey)
        )
    }
}