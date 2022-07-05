package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntry

@Composable
fun DiaryEntryItem(
    diaryEntry: DiaryEntry,
    onItemClicked: () -> Unit
) {

    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp, top = 15.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
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

                Divider(modifier = Modifier
                    .height(4.dp),
                color = Color.Transparent)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Card(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(50),
                    ) {
                        Text(
                            text = "Kcal:" + diaryEntry.nutritionValues.calories.toString(),
                            style = MaterialTheme.typography.body2,
                            color = TextGrey,
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }

                    Card(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(50),
                    ) {
                        Text(
                            text = "Carb:" + diaryEntry.nutritionValues.carbohydrates.toString(),
                            style = MaterialTheme.typography.body2,
                            color = TextGrey,
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }

                    Card(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(50),
                    ) {
                        Text(
                            text = "Prot:" + diaryEntry.nutritionValues.protein.toString(),
                            style = MaterialTheme.typography.body2,
                            color = TextGrey,
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }

                    Card(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(50),
                    ) {
                        Text(
                            text = "Fat:" + diaryEntry.nutritionValues.fat.toString(),
                            style = MaterialTheme.typography.body2,
                            color = TextGrey,
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }
    }
}