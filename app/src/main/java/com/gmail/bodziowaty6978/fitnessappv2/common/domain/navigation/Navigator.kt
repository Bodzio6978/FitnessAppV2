package com.gmail.bodziowaty6978.fitnessappv2.common.domain.navigation

import kotlinx.coroutines.flow.StateFlow

interface Navigator {
    val navActions: StateFlow<NavigationAction?>
    fun navigate(navAction: NavigationAction?)
}