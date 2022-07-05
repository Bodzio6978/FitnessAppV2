package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.OrangeYellow1
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Meal

@Composable
fun DiaryMealSection(
    meal: Meal,
    onAddProductClick: (String) -> Unit,
    wantedNutritionValues: NutritionValues = NutritionValues(
        calories = 2500*25/100,
        carbohydrates = 250.0*25.0/100.0,
        protein = 187.5*25.0/100.0,
        fat = 83.3*25.0/100.0
    )
) {

    Log.e("huj",wantedNutritionValues.toString())

    val diaryEntriesValues = meal.diaryEntries

    Card(
        shape = RoundedCornerShape(10),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(meal.mealName)
                .padding(bottom = 15.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = meal.mealName,
                    style = MaterialTheme.typography.h3,
                )

                Row {

                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier
                            .clickable {

                            }
                            .padding(10.dp)
                            .size(32.dp),
                        tint = OrangeYellow1
                    )

                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add",
                        modifier = Modifier
                            .clickable {
                                onAddProductClick(meal.mealName)
                            }
                            .padding(10.dp)
                            .size(32.dp),
                        tint = OrangeYellow1
                    )
                }
            }

            diaryEntriesValues.forEach {
                DiaryEntryItem(diaryEntry = it.diaryEntry) {

                }
            }

            Divider(
                modifier = Modifier
                    .height(16.dp),
                color = Color.Transparent
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp, start = 15.dp)
            ) {
                MealSectionNutritionItem(
                    currentValue = diaryEntriesValues.sumOf { it.diaryEntry.nutritionValues.calories },
                    wantedValue = wantedNutritionValues.calories,
                    name = stringResource(id = R.string.calories),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.width(32.dp))


                MealSectionNutritionItem(
                    currentValue = diaryEntriesValues.sumOf { it.diaryEntry.nutritionValues.carbohydrates }.toInt(),
                    wantedValue = wantedNutritionValues.carbohydrates.toInt(),
                    name = stringResource(id = R.string.carbs),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.width(32.dp))

                MealSectionNutritionItem(
                    currentValue = diaryEntriesValues.sumOf { it.diaryEntry.nutritionValues.protein }.toInt(),
                    wantedValue = wantedNutritionValues.protein.toInt(),
                    name = stringResource(id = R.string.protein),
                    modifier = Modifier

                )

                Spacer(modifier = Modifier.width(32.dp))


                MealSectionNutritionItem(
                    currentValue = diaryEntriesValues.sumOf { it.diaryEntry.nutritionValues.fat }.toInt(),
                    wantedValue = wantedNutritionValues.fat.toInt(),
                    name = stringResource(id = R.string.fat),
                    modifier = Modifier
                )
            }
        }
    }
}