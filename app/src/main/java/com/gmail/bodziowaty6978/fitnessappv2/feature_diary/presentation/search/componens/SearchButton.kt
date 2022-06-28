package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search.componens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchButton(
    text: String,
    icon: @Composable () -> Unit,
    modifier: Modifier,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .clickable {
                onClick()
            }
            .clip(RoundedCornerShape(25))
            .background(color)
            .padding(10.dp)
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        icon()

        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .padding(start = 10.dp),
            maxLines = 1
        )
    }
}