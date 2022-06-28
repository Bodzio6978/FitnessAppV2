package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gmail.bodziowaty6978.fitnessappv2.R

val Gothica = FontFamily(
    Font(R.font.gothica1_regular, FontWeight.Normal),
    Font(R.font.gothica1_medium, FontWeight.Medium),
    Font(R.font.gothica1_semibold, FontWeight.SemiBold),
    Font(R.font.gothica1_bold, FontWeight.Bold),
    Font(R.font.gothica1_black, FontWeight.Black),
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Gothica,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = TextWhite
    ),
    body2 = TextStyle(
        fontFamily = Gothica,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = TextWhite
    ),
    h1 = TextStyle(
        fontFamily = Gothica,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = TextWhite
    ),
    h2 = TextStyle(
        fontFamily = Gothica,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = TextWhite
    ),
    h3 = TextStyle(
        fontFamily = Gothica,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = TextWhite
    ),
    button = TextStyle(
        fontFamily = Gothica,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = TextWhite
    )
)