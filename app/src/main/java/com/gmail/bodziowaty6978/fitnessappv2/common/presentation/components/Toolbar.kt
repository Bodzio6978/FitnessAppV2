package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.DarkGrey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextWhite

@Composable
fun Toolbar(
    title: String,
    isBackArrowVisible: Boolean,
    onBackArrowClicked: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h1
            )
        },
        contentColor = DarkGrey,
        elevation = 0.dp,
        navigationIcon = if (isBackArrowVisible) {
            {
                IconButton(onClick = { onBackArrowClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextWhite
                    )
                }
            }
        } else {
            null
        },
    )

}