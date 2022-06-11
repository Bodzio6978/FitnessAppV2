package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Grey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.LightGrey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextWhite

@Composable
fun TextField(
    value: String,
    hint: String,
    modifier: Modifier,
    isHintVisible: Boolean = true,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit,
    keyboardType:KeyboardType = KeyboardType.Text,
    visualTransformation:VisualTransformation = VisualTransformation.None
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {

        BasicTextField(
            value = value,
            cursorBrush = SolidColor(Color.White),
            onValueChange = { onValueChange(it) },
            maxLines = 2,
            textStyle = textStyle.copy(
                color = TextWhite
            ),
            modifier = modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(30))
                .background(LightGrey)
                .padding(vertical = 12.dp, horizontal = 12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation

        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle.copy(
                    color = Color.LightGray
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .padding(vertical = 12.dp, horizontal = 12.dp)
                    .fillMaxWidth(),
            )
        }
    }
}