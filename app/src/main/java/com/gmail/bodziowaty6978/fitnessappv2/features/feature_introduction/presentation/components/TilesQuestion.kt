package com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_introduction.domain.model.Question
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG

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