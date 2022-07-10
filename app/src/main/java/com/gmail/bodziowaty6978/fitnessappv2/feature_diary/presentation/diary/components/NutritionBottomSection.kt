package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.BlueViolet3
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.LightGreen3
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.OrangeYellow3
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Meal
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.sumNutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.util.NutritionType

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
                .weight(1F),
            progressColor = LightGreen3
        )

        NutritionBottomItem(
            currentValue = meals.sumOf { it.sumNutritionValues(NutritionType.Protein) }.toInt(),
            wantedValue = wantedNutritionValues.protein.toInt(),
            name = stringResource(id = R.string.protein),
            modifier = Modifier
                .weight(1F),
            progressColor = BlueViolet3
        )

        NutritionBottomItem(
            currentValue = meals.sumOf { it.sumNutritionValues(NutritionType.Fat) }.toInt(),
            wantedValue = wantedNutritionValues.fat.toInt(),
            name = stringResource(id = R.string.fat),
            modifier = Modifier
                .weight(1F),
            progressColor = OrangeYellow3
        )
    }
    
}