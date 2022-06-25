package com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator

import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.model.NavigationAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ComposeCustomNavigator:Navigator {

    private val _navActions: MutableStateFlow<NavigationAction?> by lazy {
        MutableStateFlow(null)
    }

    override val navActions: StateFlow<NavigationAction?> = _navActions.asStateFlow()

    override fun navigate(navAction: NavigationAction?) {
        _navActions.update { navAction }
    }

}