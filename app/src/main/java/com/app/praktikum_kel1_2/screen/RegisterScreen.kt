package com.app.praktikum_kel1_2.screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.praktikum_kel1_2.model.request.RegisterRequest
import com.app.praktikum_kel1_2.navigation.Screen
import com.app.praktikum_kel1_2.service.api.ApiClient
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavHostController) {
    // State
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            Text(
                text = "Buat Akun Baru",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Input Fields
            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    fullNameError = false
                },
                label = { Text("Nama Lengkap") },
                isError = fullNameError,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
            if (fullNameError) {
                Text("Nama lengkap wajib diisi", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    usernameError = false
                },
                label = { Text("Username") },
                isError = usernameError,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
            if (usernameError) {
                Text("Username wajib diisi", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = false
                },
                label = { Text("Email") },
                isError = emailError,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
            if (emailError) {
                Text("Email tidak valid", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = false
                },
                label = { Text("Password") },
                isError = passwordError,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
            if (passwordError) {
                Text("Password wajib diisi", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = false
                },
                label = { Text("Konfirmasi Password") },
                isError = confirmPasswordError,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
            if (confirmPasswordError) {
                Text("Password tidak cocok", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Register
            Button(
                onClick = {
                    focusManager.clearFocus()

                    fullNameError = fullName.isBlank()
                    usernameError = username.isBlank()
                    emailError = email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    passwordError = password.isBlank()
                    confirmPasswordError = confirmPassword != password

                    if (!fullNameError && !usernameError && !emailError && !passwordError && !confirmPasswordError) {
                        isLoading = true
                        coroutineScope.launch {
                            try {
                                val response = ApiClient.instance.register(
                                    RegisterRequest(
                                        nm_lengkap = fullName,
                                        email = email,
                                        username = username,
                                        password = password
                                    )
                                )
                                isLoading = false
                                val body = response.body()

                                if (response.isSuccessful && body != null) {
                                    Toast.makeText(context, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(Screen.Register.route) { inclusive = true }
                                    }
                                } else {
                                    val errorMsg = body?.message ?: response.message()
                                    Toast.makeText(context, "Gagal: $errorMsg", Toast.LENGTH_LONG).show()
                                }
                            } catch (e: Exception) {
                                isLoading = false
                                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Daftar", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = { navController.navigate(Screen.Login.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sudah punya akun? Masuk di sini")
            }
        }
    }

    // Dialog loading
    if (isLoading) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {},
            title = { Text("Mohon tunggu") },
            text = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Sedang mengirim data...")
                }
            }
        )
    }
}
