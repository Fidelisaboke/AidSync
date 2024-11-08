package com.example.aidsync.ui.patients

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aidsync.ui.theme.AidSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(
    navController: NavController,  // NavController parameter
    onProfileClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onThemeClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
) {
    // State to manage the visibility of the confirmation dialog
    var showDialog by remember { mutableStateOf(false) }

    // Scaffold for the Settings Page UI
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings", fontWeight = FontWeight.Bold) })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Profile Management
                SettingItem(
                    text = "Profile Management",
                    onClick = { navController.navigate("profileManagement") }
                )

                // Notification Preferences
                SettingItem(
                    text = "Notification Preferences",
                    onClick = onNotificationsClick
                )

                // Theme Settings
                SettingItem(
                    text = "Theme Settings",
                    onClick = onThemeClick
                )

                // Language Settings
                SettingItem(
                    text = "Language Settings",
                    onClick = onLanguageClick
                )

                // Privacy Settings
                SettingItem(
                    text = "Privacy Settings",
                    onClick = onPrivacyClick
                )

                // Help & Support
                SettingItem(
                    text = "Help & Support",
                    onClick = onHelpClick
                )

                // Logout Button
                Button(
                    onClick = { showDialog = true },  // Show confirmation dialog
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Logout")
                }
            }

            // Confirmation Dialog
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Are you sure?") },
                    text = { Text("Do you want to log out?") },
                    confirmButton = {
                        TextButton(onClick = {
                            // Navigate to home screen upon confirmation
                            navController.navigate("home")
                            showDialog = false
                        }) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("No")
                        }
                    }
                )
            }
        }
    )
}

@Composable
fun SettingItem(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPagePreview() {
    AidSyncTheme {
        SettingsPage(navController = rememberNavController())
    }
}
