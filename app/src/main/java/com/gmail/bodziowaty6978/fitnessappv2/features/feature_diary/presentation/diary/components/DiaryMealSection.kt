package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Grey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.LightGrey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Yellow
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.Meal
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG

@Composable
fun DiaryMealSection(
    meal:Meal
) {
    val diaryEntriesValues = meal.diaryEntries

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .testTag(meal.mealName)
    ) {

        Text(
            text = meal.mealName,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = if (diaryEntriesValues.isEmpty()) 5.dp else 0.dp)
        )

        if (diaryEntriesValues.isNotEmpty()){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 5.dp)
            ) {

                Text(
                    text = "${diaryEntriesValues.sumOf { it.diaryEntry.calories }} kcal",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier.weight(1F)
                )

                Text(
                    text = "${diaryEntriesValues.sumOf { it.diaryEntry.carbs }}g",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${diaryEntriesValues.sumOf { it.diaryEntry.protein }}g",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${diaryEntriesValues.sumOf { it.diaryEntry.fat }}g",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.Center
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .testTag(meal.mealName+"LazyColumn")
        ){
            Log.e(TAG,meal.mealName+"LazyColumn")
            items(diaryEntriesValues.size){
                DiaryEntryItem(
                    diaryEntry = diaryEntriesValues[it].diaryEntry,
                    onItemClicked = {

                    }
                )
            }
        }

        Divider(
            color = LightGrey,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Grey)
                .clickable {

                }
                .padding(vertical = 8.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add icon",
                tint = Yellow
            )

            Text(
                text = stringResource(id = R.string.add_product),
                style = MaterialTheme.typography.body1,
                color = Yellow,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )

        }
    }
}