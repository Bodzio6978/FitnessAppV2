package com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator

import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.model.NavigationAction
import kotlinx.coroutines.flow.StateFlow

interface Navigator {
    val navActions: StateFlow<NavigationAction?>
    fun navigate(navAction:NavigationAction?)
}