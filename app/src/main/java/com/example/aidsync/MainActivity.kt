package com.example.aidsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.aidsync.ui.patients.HomeScreen
import com.example.aidsync.ui.patients.Login
import com.example.aidsync.ui.patients.Register
import com.example.aidsync.ui.theme.AidSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AidSyncTheme {
                val navController = rememberNavController() // Create NavController for navigation

                // Set up NavHost for managing navigation
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onLoginClick = { navController.navigate("login") },
                            onRegisterClick = { navController.navigate("register") }
                        )
                    }
                    composable("login") {
                        Login(
                            onLoginSuccess = { navController.navigate("home") },
                            onNavigateToRegister = { navController.navigate("register") }
                        )
                    }
                    composable("register") {
                        Register(
                            onRegisterSuccess = { navController.navigate("home") },
                            onNavigateToLogin = { navController.navigate("login") }
                        )
                    }
                }
            }
        }
    }
}
