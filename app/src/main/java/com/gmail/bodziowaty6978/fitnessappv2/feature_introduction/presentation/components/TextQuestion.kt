package com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.LightGrey

@Composable
fun TextQuestion(
    text: String,
    unit: String = "",
    onTextEntered: (String) -> Unit,
    tag:String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                onTextEntered(it.replace(",","."))
            },
            textStyle = MaterialTheme.typography.h2.copy(
                textAlign = TextAlign.Center
            ),
            cursorBrush = SolidColor(Color.White),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .width(80.dp)
                .clip(RoundedCornerShape(50))
                .background(LightGrey)
                .padding(horizontal = 10.dp, vertical = 7.dp)
                .testTag(tag)
        )

        Text(
            text = unit,
            style = MaterialTheme.typography.body1.copy(
                color = Color.White
            ),
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}