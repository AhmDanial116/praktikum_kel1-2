package com.app.praktikum_kel1_2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.praktikum_kel1_2.screen.HomeScreen
import com.app.praktikum_kel1_2.screen.ResultScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Result.route,
            arguments = listOf(navArgument ("text") {
                type = NavType.StringType
            })
        ) {
            ResultScreen(it.arguments?.getString("text").toString(), navController)
        }
    }
}