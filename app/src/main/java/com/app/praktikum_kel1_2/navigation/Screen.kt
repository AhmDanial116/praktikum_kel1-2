package com.app.praktikum_kel1_2.navigation

sealed class Screen(val route: String){
    object Home: Screen(route = "home_screen")

    object Result: Screen(route = "result_screen/{text}") {
        fun passText(text: String): String {
            return "result_screen/$text"
        }
    }
}