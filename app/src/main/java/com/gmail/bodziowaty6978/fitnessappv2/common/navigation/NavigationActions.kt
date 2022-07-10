package com.gmail.bodziowaty6978.fitnessappv2.common.navigation

import android.os.Parcelable
import androidx.navigation.NavOptions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.model.NavigationAction
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.BottomBarScreen
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.Screen
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.util.AuthScreen

object NavigationActions {

    object General{
        fun navigateUp() = object :NavigationAction{
            override val destination: String = "navigateUp"
        }
    }

    //Auth
    object LoginScreen{
        fun loginToRegister() = object : NavigationAction {
            override val destination: String = AuthScreen.RegisterAuthScreen.route
        }
        fun loginToReset() = object : NavigationAction {
            override val destination: String = AuthScreen.ResetPasswordAuthScreen.route
        }
        fun loginToLoading() = object : NavigationAction {
            override val destination: String = Screen.LoadingScreen.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }
    }
    object RegisterScreen{
        fun registerToLogin() = object : NavigationAction {
            override val destination: String
                get() = AuthScreen.LoginAuthScreen.route
        }
        fun registerToLoading() = object : NavigationAction {
            override val destination: String = Screen.LoadingScreen.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }

    }
    object ResetScreen{
        fun resetToLogin() = object : NavigationAction {
            override val destination: String = AuthScreen.LoginAuthScreen.route
        }
    }



    //Introducion
    object IntroductionScreen{
        fun introductionToSummary() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Summary.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }
    }



    //Loading
    object LoadingScreen{
        fun loadingToIntroduction() = object : NavigationAction {
            override val destination: String = Screen.IntroductionScreen.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }
        fun loadingToLogin() = object : NavigationAction {
            override val destination: String = AuthScreen.LoginAuthScreen.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }
        fun loadingToSummary() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Summary.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }
    }

    //Summary
    object SummaryScreen{
        fun summaryToDiary() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Diary.route
        }
        fun summaryToAccount() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Account.route
        }
    }

    //Account
    object AccountScreen{
        fun accountToDiary() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Diary.route
        }
        fun accountToSummary() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Summary.route
        }
    }

    //Diary
    object DiaryScreen{
        fun diaryToAccount() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Account.route

        }
        fun diaryToSummary() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Summary.route
        }
        fun diaryToSearch(mealName:String) = object : NavigationAction {
            override val destination: String = Screen.SearchScreen.route + "?mealName=$mealName"
        }
    }


    //Search
    object SearchScreen{
        fun searchToDiary() = object : NavigationAction{
            override val destination: String = BottomBarScreen.Diary.route
        }
        fun searchToProduct(productWithId: Parcelable, mealName: String) = object : NavigationAction{
            override val destination: String = Screen.ProductScreen.route + "?mealName=$mealName"
            // really weird I don`t know why it works but it works
            override val parcelableArguments: Map<String, Parcelable>
                get() = mapOf(Pair("productWithId",productWithId),Pair("ProductWithId",productWithId))
        }
    }


    //Product
    object ProductScreen{
        fun productToDiary() = object : NavigationAction {
            override val destination: String = BottomBarScreen.Diary.route
            override val navOptions: NavOptions = NavOptions.Builder().setPopUpTo(0,true).build()
        }
    }
}