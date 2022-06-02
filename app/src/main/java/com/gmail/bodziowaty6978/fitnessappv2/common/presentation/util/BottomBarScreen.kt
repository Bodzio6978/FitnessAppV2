package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Summary : BottomBarScreen(
        route = "summary_screen",
        title = "Summary",
        icon = Icons.Default.Home
    )
    object Diary : BottomBarScreen(
        route = "diary_screen",
        title = "Diary",
        icon = Icons.Default.Book
    )
    object Account : BottomBarScreen(
        route = "account_screen",
        title = "Account",
        icon = Icons.Default.AccountCircle
    )
}