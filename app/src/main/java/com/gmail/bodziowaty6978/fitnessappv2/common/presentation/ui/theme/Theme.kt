package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ButtonBlue,
    primaryVariant = OrangeYellow1,
    secondary = LightGreen1,
    secondaryVariant = BlueViolet1,
    background = DarkGrey,
    surface = DarkGrey,
    onPrimary = DarkGrey,
    onSecondary = DarkGrey,
    onBackground = TextWhite,
    onSurface = TextWhite,
    error = LightRed
)


@Composable
fun FitnessAppV2Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}