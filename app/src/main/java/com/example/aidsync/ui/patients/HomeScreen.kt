package com.example.aidsync.ui.patients

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aidsync.ui.theme.AidSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                actions = {
                    // Login Button
                    Button(
                        onClick = onLoginClick,
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        Text("Login")
                    }
                    // Register Button
                    Spacer(modifier = Modifier.width(8.dp))  // Add some space between buttons
                    Button(
                        onClick = onRegisterClick,
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        Text("Register")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome to the First Aid App",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Use the app to manage casualties, track medical records, and more.",
                    fontSize = 16.sp
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AidSyncTheme {
        HomeScreen(onLoginClick = {}, onRegisterClick = {})
    }
}
