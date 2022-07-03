package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    shape : Shape = RoundedCornerShape(50),
    textStyle: TextStyle = MaterialTheme.typography.body1,
    singleLine:Boolean = false
) {
    TextField(
        value = value,
        placeholder = placeholder,
        onValueChange = {
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = shape,
        modifier = modifier,
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = textStyle,
        singleLine = singleLine
    )
}