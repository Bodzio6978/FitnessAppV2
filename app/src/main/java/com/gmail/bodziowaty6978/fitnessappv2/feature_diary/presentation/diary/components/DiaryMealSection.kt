package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components

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
        calories = 2500,
        carbohydrates = 250.0,
        protein = 187.5,
        fat = 83.3
    )
) {

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
                DiaryEntryItem(diaryEntry = it.diaryEntry, modifier = Modifier.fillMaxWidth()) {

                }
            }



//            FlowColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .testTag(meal.mealName + "LazyColumn")
//            ) {
//                diaryEntriesValues.forEach {
//                    Card(
//                        modifier = Modifier
//                        .fillMaxWidth(),
//                        elevation = 8.dp
//                    ) {
//
//                        Row(
//                            modifier = Modifier
//                            .fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            DiaryEntryItem(diaryEntry = it.diaryEntry, Modifier.fillMaxWidth()) {
//
//                            }
//                        }
//                    }
//                }
//            }

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
                    currentValue = diaryEntriesValues.sumOf { it.diaryEntry.calories },
                    wantedValue = wantedNutritionValues.calories,
                    name = stringResource(id = R.string.calories),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.width(32.dp))


                MealSectionNutritionItem(
                    currentValue = diaryEntriesValues.sumOf { it.diaryEntry.carbs }.toInt(),
                    wantedValue = wantedNutritionValues.carbohydrates.toInt(),
                    name = stringResource(id = R.string.carbs),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.width(32.dp))

                MealSectionNutritionItem(
                    currentValue = diaryEntriesValues.sumOf { it.diaryEntry.protein }.toInt(),
                    wantedValue = wantedNutritionValues.protein.toInt(),
                    name = stringResource(id = R.string.protein),
                    modifier = Modifier

                )

                Spacer(modifier = Modifier.width(32.dp))


                MealSectionNutritionItem(
                    currentValue = diaryEntriesValues.sumOf { it.diaryEntry.fat }.toInt(),
                    wantedValue = wantedNutritionValues.fat.toInt(),
                    name = stringResource(id = R.string.fat),
                    modifier = Modifier
                )
            }
        }
    }


//    val diaryEntriesValues = meal.diaryEntries
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 10.dp)
//            .testTag(meal.mealName)
//    ) {
//
//        Text(
//            text = meal.mealName,
//            style = MaterialTheme.typography.body1,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .padding(horizontal = 20.dp)
//                .padding(bottom = if (diaryEntriesValues.isEmpty()) 5.dp else 0.dp)
//        )
//
//        if (diaryEntriesValues.isNotEmpty()) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .padding(bottom = 5.dp)
//            ) {
//
//                Text(
//                    text = "${diaryEntriesValues.sumOf { it.diaryEntry.calories }} kcal",
//                    style = MaterialTheme.typography.body2,
//                    color = TextGrey,
//                    modifier = Modifier.weight(1F)
//                )
//
//                Text(
//                    text = "${diaryEntriesValues.sumOf { it.diaryEntry.carbs }}g",
//                    style = MaterialTheme.typography.body2,
//                    color = TextGrey,
//                    modifier = Modifier.weight(1F),
//                    textAlign = TextAlign.Center
//                )
//
//                Text(
//                    text = "${diaryEntriesValues.sumOf { it.diaryEntry.protein }}g",
//                    style = MaterialTheme.typography.body2,
//                    color = TextGrey,
//                    modifier = Modifier.weight(1F),
//                    textAlign = TextAlign.Center
//                )
//
//                Text(
//                    text = "${diaryEntriesValues.sumOf { it.diaryEntry.fat }}g",
//                    style = MaterialTheme.typography.body2,
//                    color = TextGrey,
//                    modifier = Modifier.weight(1F),
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//

//
//        Divider(
//            color = TextGrey,
//            modifier = Modifier
//                .fillMaxWidth()
//                .width(1.dp)
//        )
//
//        ExtendedFloatingActionButton(
//            text = {
//                Text(
//                    text = stringResource(id = R.string.add_product),
//                    style = MaterialTheme.typography.body1,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(start = 10.dp)
//                )
//            },
//            onClick = {
//                onAddProductClick(meal.mealName)
//            },
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add icon",
//                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
//                )
//            })
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp)
//                .clickable {
//                    onAddProductClick(meal.mealName)
//                }
//                .padding(vertical = 8.dp)
//                .background(
//                    if (isSystemInDarkTheme()) MaterialTheme.colors.background else DarkPink
//                )
//                .clip(RoundedCornerShape(25))
//                .shadow(elevation = 0.85.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = "Add icon",
//                tint = MaterialTheme.colors.secondaryVariant,
//                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
//            )
//
//            Text(
//                text = stringResource(id = R.string.add_product),
//                style = MaterialTheme.typography.body1,
//                color = MaterialTheme.colors.secondaryVariant,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(start = 10.dp)
//            )
//
//        }
//    }
}