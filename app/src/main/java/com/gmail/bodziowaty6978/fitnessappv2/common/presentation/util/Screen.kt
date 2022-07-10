package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util

sealed class Screen(val route:String) {
    object IntroductionScreen: Screen(route = "introduction_screen")
    object LoadingScreen: Screen(route = "loading_screen")
    object SearchScreen: Screen(route = "search_screen")
    object ProductScreen: Screen(route = "product_screen")
    object NewProductScreen : Screen(route = "new_product_screen")
}