package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Blue

@Composable
fun HorizontalProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float = 0.0001F,
) {
    val mProgress = animateFloatAsState(targetValue = progress / 100)

    Column(
        modifier = modifier
            .height(30.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .width(6.dp),
    ) {
        Box(
            modifier = Modifier
                .weight(if ((1F - mProgress.value) == 0F) 0.0001F else 1 - mProgress.value)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .weight(if (mProgress.value==0F) 0.0001F else mProgress.value)
                .fillMaxWidth()
                .background(
                    Blue
                )
        )
    }
}