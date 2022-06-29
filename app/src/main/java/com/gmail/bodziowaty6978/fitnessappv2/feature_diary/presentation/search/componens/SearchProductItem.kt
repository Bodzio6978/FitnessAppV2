package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search.componens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.AquaBlue
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchProductItem(
    product:Product,
    onItemClick:() -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = 6.dp,
        onClick = {
            onItemClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "${product.containerWeight}${product.unit}",
                    style = MaterialTheme.typography.body2.copy(
                        color = AquaBlue
                    )
                )
            }

            Text(
                text = product.calories.toString()+" kcal",
                style = MaterialTheme.typography.body2.copy(
                    color = AquaBlue
                )
            )

        }
    }


}