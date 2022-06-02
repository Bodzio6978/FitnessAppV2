package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Grey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey

@Composable
fun DiaryEntryItem(
    diaryEntry: DiaryEntry,
    onItemClicked:() -> Unit
) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClicked()
                }
                .background(Grey)
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = diaryEntry.name,
                    style = MaterialTheme.typography.body1
                )

                Text(
                    text = "(${diaryEntry.weight}${diaryEntry.unit})",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier
                        .padding(start = 3.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "${diaryEntry.calories} kcal",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier.weight(1F)
                )

                Text(
                    text = "${diaryEntry.carbs}g",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${diaryEntry.protein}g",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${diaryEntry.fat}g",
                    style = MaterialTheme.typography.body2,
                    color = TextGrey,
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.Center

                )



            }

    }
}