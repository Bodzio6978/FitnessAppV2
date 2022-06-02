package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Meal
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.sumNutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.util.NutritionType

@Composable
fun NutritionBottomSection(
    meals: List<Meal>,
    wantedNutritionValues: NutritionValues,
    modifier: Modifier
) {


    Row(
        modifier = modifier
    ) {
        NutritionBottomItem(
            currentValue =  meals.sumOf { it.sumNutritionValues(NutritionType.Calories) }.toInt(),
            wantedValue = wantedNutritionValues.calories,
            name = stringResource(id = R.string.calories),
            modifier = Modifier
                .weight(1F)
        )

        NutritionBottomItem(
            currentValue = meals.sumOf { it.sumNutritionValues(NutritionType.Carbs) }.toInt(),
            wantedValue = wantedNutritionValues.carbohydrates.toInt(),
            name = stringResource(id = R.string.carbs),
            modifier = Modifier
                .weight(1F)
        )

        NutritionBottomItem(
            currentValue = meals.sumOf { it.sumNutritionValues(NutritionType.Protein) }.toInt(),
            wantedValue = wantedNutritionValues.protein.toInt(),
            name = stringResource(id = R.string.protein),
            modifier = Modifier
                .weight(1F)
        )

        NutritionBottomItem(
            currentValue = meals.sumOf { it.sumNutritionValues(NutritionType.Fat) }.toInt(),
            wantedValue = wantedNutritionValues.fat.toInt(),
            name = stringResource(id = R.string.fat),
            modifier = Modifier
                .weight(1F)
        )
    }
    
}