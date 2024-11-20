package com.example.aidsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import com.example.aidsync.ui.casualtyreport.CasualtyReportScreen
import com.example.aidsync.ui.patients.HomeScreen
import com.example.aidsync.ui.patients.LandingPage
import com.example.aidsync.ui.patients.Login
import com.example.aidsync.ui.patients.ProfileManagementPage
import com.example.aidsync.ui.patients.Register
import com.example.aidsync.ui.patients.SettingsPage
import com.example.aidsync.ui.theme.AidSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AidSyncTheme {
                val navController = rememberNavController()

                // NavHost to manage navigation
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onLoginClick = { navController.navigate("login") },
                            onRegisterClick = { navController.navigate("register") }
                        )
                    }
                    composable("login") {
                        Login(
                            onLoginSuccess = {
                                navController.navigate("landing") {
                                    popUpTo("home") { inclusive = true }
                                }
                            },
                            onNavigateToRegister = { navController.navigate("register") }
                        )
                    }
                    composable("register") {
                        Register(
                            onRegisterSuccess = {
                                navController.navigate("landing") {
                                    popUpTo("home") { inclusive = true }
                                }
                            },
                            onNavigateToLogin = { navController.navigate("login") }
                        )
                    }
                    composable("landing") {
                        LandingPage(
                            onSettingsClick = { navController.navigate("settings") },
                            onCasualtyReportClick = { navController.navigate("casualtyReport") } // Navigation to casualty report
                        )
                    }
                    composable("settings") {
                        SettingsPage(
                            navController = navController,
                            onProfileClick = { navController.navigate("profileManagement") }
                        )
                    }
                    composable("profileManagement") {
                        ProfileManagementPage(navController = navController)
                    }
                    composable("casualtyReport") {
                        CasualtyReportScreen() // Route to CasualtyReportScreen
                    }
                }
            }
        }
    }
}
