package com.gmail.bodziowaty6978.fitnessappv2.common.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.BottomBar
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.BottomBarScreen
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.util.Screen
import com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions.asLifecycleAwareState
import com.gmail.bodziowaty6978.fitnessappv2.feature_account.presentation.AccountScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.login.LoginScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.register.RegisterScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.reset_password.ResetPasswordScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.presentation.util.AuthScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary.DiaryScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.ProductScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search.SearchScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation.IntroductionScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_splash.loading.presentation.SplashScreen
import com.gmail.bodziowaty6978.fitnessappv2.feature_summary.presentation.SummaryScreen

@Composable
fun NavHostGraph(
    navController: NavHostController = rememberNavController(),
    navigator: Navigator,
    startDestination:String = Screen.ProductScreen.route + "?mealName={mealName}"
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigatorState by navigator.navActions.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = null
    )

    var bottomNavigationState by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(navigatorState) {
        navigatorState?.let {
            it.parcelableArguments.forEach { argument ->
                navController.currentBackStackEntry?.arguments?.putParcelable(
                    argument.key,
                    argument.value
                )
            }
            if (it.destination == "navigateUp") {
                navController.navigateUp()
            } else {
                navController.navigate(it.destination, it.navOptions)
            }
        }
    }


    Scaffold(
        bottomBar = {
            if (bottomNavigationState) {
                BottomBar(navController = navController)
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(
                    route = Screen.LoadingScreen.route
                ) {
                    bottomNavigationState = false
                    SplashScreen()
                }

                composable(
                    route = Screen.IntroductionScreen.route
                ) {
                    bottomNavigationState = false
                    IntroductionScreen()
                }

                composable(
                    route = AuthScreen.LoginAuthScreen.route
                ) {
                    bottomNavigationState = false
                    LoginScreen()
                }
                composable(
                    route = AuthScreen.RegisterAuthScreen.route
                ) {
                    bottomNavigationState = false
                    RegisterScreen()
                }
                composable(
                    route = AuthScreen.ResetPasswordAuthScreen.route
                ) {
                    bottomNavigationState = false
                    ResetPasswordScreen()
                }
                composable(
                    route = BottomBarScreen.Summary.route
                ) {
                    bottomNavigationState = true
                    SummaryScreen()
                }

                composable(
                    route = BottomBarScreen.Diary.route
                ) {
                    bottomNavigationState = true
                    DiaryScreen(
                        paddingValues = paddingValues
                    )
                }

                composable(
                    route = BottomBarScreen.Account.route
                ) {
                    bottomNavigationState = true
                    AccountScreen()
                }
                composable(
                    route = Screen.SearchScreen.route + "?mealName={mealName}",
                    arguments = listOf(
                        navArgument(
                            name = "mealName"
                        ) {
                            type = NavType.StringType
                            defaultValue = "Breakfast"
                        }
                    )
                ) { backStackEntry ->
                    bottomNavigationState = false
                    val mealName = backStackEntry.arguments?.getString("mealName")
                    SearchScreen(mealName!!)
                }

                composable(
                    route = Screen.ProductScreen.route + "?mealName={mealName}",
                    arguments = listOf(
                        navArgument(
                            name = "mealName"
                        ) {
                            type = NavType.StringType
                            defaultValue = "Breakfast"
                        }
                    )
                ) {
                    val productWithId = navController.previousBackStackEntry?.arguments?.getParcelable<ProductWithId>(
                            "productWithId"
                        )
                    val mealName = it.arguments?.getString("mealName")
                    if (productWithId != null) {
                        ProductScreen(productWithId = productWithId, mealName = mealName!!)
                    } else {
                        ProductScreen(
                            productWithId = ProductWithId(
                                product = Product(),
                                productId = ""
                            ),
                            mealName = mealName!!
                        )
                    }

                }
            }
        }
    }


}