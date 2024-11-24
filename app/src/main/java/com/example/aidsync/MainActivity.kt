package com.example.aidsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import com.example.aidsync.ui.casualtyreport.CasualtyReportScreen
import com.example.aidsync.ui.firstaid.FirstAidDetailsScreen
import com.example.aidsync.ui.firstaid.FirstAidTopicsPage
import com.example.aidsync.ui.patients.HomeScreen
import com.example.aidsync.ui.patients.LandingPage
import com.example.aidsync.ui.authentication.LoginScreen
import com.example.aidsync.ui.settings.ProfileManagementPage
import com.example.aidsync.ui.authentication.RegisterScreen
import com.example.aidsync.ui.emergency.EmergencyHotlinesPage
import com.example.aidsync.ui.settings.SettingsPage
import com.example.aidsync.ui.patienttracker.PatientCareTrackerScreen // Import PatientTrackerScreen
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
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate("landing") {
                                    popUpTo("home") { inclusive = true }
                                }
                            },
                            onNavigateToRegister = { navController.navigate("register") }
                        )
                    }
                    composable("register") {
                        RegisterScreen(
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
                            onFirstAidTopicsClick = { navController.navigate("firstAidTopics") },
                            onCasualtyReportClick = { navController.navigate("casualtyReport") },
                            onPatientTrackerClick = { navController.navigate("patientTracker") }, // Navigate to PatientTrackerScreen
                            onEmergencyHotlinesClick = { navController.navigate("emergencyHotlines") }
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
                        CasualtyReportScreen()
                    }
                    composable("firstAidTopics") {
                        FirstAidTopicsPage(
                            navController = navController,
                            onBackClick = { navController.navigateUp() }
                        )
                    }
                    composable("firstAidDetails/{topic}") { backStackEntry ->
                        val topic = backStackEntry.arguments?.getString("topic") ?: ""
                        FirstAidDetailsScreen(
                            topic = topic,
                            navController = navController
                        )
                    }
                    composable("emergencyHotlines") {
                        EmergencyHotlinesPage(
                            onBackClick = { navController.navigateUp() }
                        )
                    }
                    composable("patientTracker") {
                        PatientCareTrackerScreen() // Route to PatientCareTrackerScreen
                    }
                }
            }
        }
    }
}
