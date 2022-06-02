package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.BottomBarScreen

@Composable
fun RowScope.AddItem(
    bottomBarScreen: BottomBarScreen,
    currentDestination:NavDestination?,
    navController:NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = bottomBarScreen.title)
        },
        icon = {
            Icon(imageVector = bottomBarScreen.icon, contentDescription = bottomBarScreen.title)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == bottomBarScreen.route
        } == true,
        onClick = {
            navController.navigate(bottomBarScreen.route)
        }

    )
}