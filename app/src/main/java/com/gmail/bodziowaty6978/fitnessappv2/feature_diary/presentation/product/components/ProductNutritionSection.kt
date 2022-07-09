package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.BlueViolet2
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.BlueViolet3
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.LightGreen3
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.OrangeYellow2

@Composable
fun ProductNutritionSection(
    nutritionData: NutritionData
) {

    Card(
        elevation = 3.dp,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(15)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(bottom = 15.dp, end = 15.dp, top = 15.dp, start = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                Modifier.weight(0.4F),
                verticalArrangement = Arrangement.Center
            ) {
                ChartSection(
                    nutritionData = nutritionData,
                )
            }

            Column(
                Modifier
                    .weight(0.8F)
                    .fillMaxHeight()
            ) {
                Column(

                ) {

                    val nutritionValues = nutritionData.nutritionValues

                    Text(
                        text = stringResource(id = R.string.nutrition_values),
                        style = MaterialTheme.typography.h3
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.carbohydrates),
                            style = MaterialTheme.typography.body2.copy(
                                color = LightGreen3
                            )
                        )

                        Text(
                            text = "${nutritionValues.carbohydrates}g",
                            style = MaterialTheme.typography.body2.copy(
                                color = LightGreen3
                            )
                        )

                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.protein),
                            style = MaterialTheme.typography.body2.copy(
                                color = BlueViolet3
                            )
                        )

                        Text(
                            text = "${nutritionValues.protein}g",
                            style = MaterialTheme.typography.body2.copy(
                                color = BlueViolet2
                            )
                        )

                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.fat),
                            style = MaterialTheme.typography.body2.copy(
                                color = OrangeYellow2
                            )
                        )

                        Text(
                            text = "${nutritionValues.fat}g",
                            style = MaterialTheme.typography.body2.copy(
                                color = OrangeYellow2
                            )
                        )

                    }

                }
            }
        }

    }
}