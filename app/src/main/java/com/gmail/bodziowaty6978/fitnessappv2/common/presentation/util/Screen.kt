package com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util

sealed class Screen(val route:String) {
    object IntroductionScreen: Screen(route = "introduction_screen")
    object SplashScreen: Screen(route = "splash_screen")
}