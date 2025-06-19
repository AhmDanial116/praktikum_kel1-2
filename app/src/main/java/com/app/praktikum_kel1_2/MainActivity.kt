package com.app.praktikum_kel1_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.app.praktikum_kel1_2.navigation.SetupNavGraph
import com.app.praktikum_kel1_2.ui.theme.Praktikum_kel12Theme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Praktikum_kel12Theme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Praktikum_kel12Theme {
        val navController = rememberNavController()
        SetupNavGraph(navController = navController)
    }
}