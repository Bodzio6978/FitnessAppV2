package com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.model.Question

@Composable
fun TilesQuestion(
    question: Question,
    onItemClick:(Int) -> Unit,
    currentItem:String
) {

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {

        question.tiles.forEachIndexed { index, content ->
            Tile(
                content = content,
                isSelected = index==currentItem.toInt(),
                onItemClick = {
                    onItemClick(index)
                }
            )
        }

    }


}