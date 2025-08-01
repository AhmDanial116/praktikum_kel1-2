package com.app.praktikum_kel1_2.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.praktikum_kel1_2.navigation.Screen

@Composable
fun ResultScreen(name: String, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Halo, $name!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Selamat datang di halaman hasil.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate(route = Screen.Home.route) {
                        popUpTo(Screen.Result.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Kembali", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    ResultScreen(
        name = "",
        navController = rememberNavController()
    )
}
