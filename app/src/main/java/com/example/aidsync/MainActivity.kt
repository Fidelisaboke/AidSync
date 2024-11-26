package com.example.aidsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
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
import com.example.aidsync.ui.theme.AidSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AidSyncApp()
        }
    }
}

@Composable
fun AidSyncApp() {
    AidSyncTheme {
        val navController = rememberNavController()
        NavHostControllerSetup(navController = navController)
    }
}

@Composable
fun NavHostControllerSetup(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        // Home Screen
        composable("home") {
            HomeScreen(
                onLoginClick = { navController.navigate("login") },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        // Login Screen
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

        // Register Screen
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

        // Landing Page
        composable("landing") {
            LandingPage(
                onSettingsClick = { navController.navigate("settings") },
                onFirstAidTopicsClick = { navController.navigate("firstAidTopics") },
                onCasualtyReportClick = { navController.navigate("casualtyReport") },
                onEmergencyHotlinesClick = { navController.navigate("emergencyHotlines") }
            )
        }

        // Settings Page
        composable("settings") {
            SettingsPage(
                navController = navController,
                onProfileClick = { navController.navigate("profileManagement") }
            )
        }

        // Profile Management Page
        composable("profileManagement") {
            ProfileManagementPage(navController = navController)
        }

        // Casualty Report Screen
        composable("casualtyReport") {
            CasualtyReportScreen(navController = navController)
        }

        // First Aid Topics Page
        composable("firstAidTopics") {
            FirstAidTopicsPage(
                navController = navController,
                onBackClick = { navController.navigateUp() }
            )
        }

        // First Aid Details Screen
        composable("firstAidDetails/{topic}") { backStackEntry ->
            val topic = backStackEntry.arguments?.getString("topic") ?: ""
            FirstAidDetailsScreen(
                topic = topic,
                navController = navController
            )
        }

        // Emergency Hotlines Page
        composable("emergencyHotlines") {
            EmergencyHotlinesPage(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}
