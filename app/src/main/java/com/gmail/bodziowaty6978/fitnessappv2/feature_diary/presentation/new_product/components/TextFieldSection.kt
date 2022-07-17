package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.DefaultTextField

@Composable
fun TextFieldSection(
    name: String,
    textFieldValue: String,
    modifier: Modifier,
    onTextEntered: (String) -> Unit,
    textFieldOptions:KeyboardOptions = KeyboardOptions.Default
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body1
        )

        DefaultTextField(
            value = textFieldValue,
            onValueChange = {
                onTextEntered(it)
            },
            modifier = modifier,
            keyboardOptions = textFieldOptions
        )

    }

}