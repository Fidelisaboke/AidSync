package com.example.aidsync.ui.patients

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aidsync.R
import com.example.aidsync.ui.theme.AidSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage(
    onManageCasualtiesClick: () -> Unit = {},
    onViewProtocolsClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome to AidSync", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Image
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "AidSync Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp)
                )

                // Welcome Message
                Text(
                    text = "Manage First Aid Effortlessly",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Navigate through key features to assist in providing timely and effective first aid.",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 24.dp),
                    color = Color.Gray
                )

                // Manage Casualties Button
                Button(
                    onClick = onManageCasualtiesClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text("Manage Casualties")
                }

                // View Protocols Button
                Button(
                    onClick = onViewProtocolsClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text("View Protocols")
                }

                // Settings Button
                OutlinedButton(
                    onClick = onSettingsClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Settings")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LandingPagePreview() {
    AidSyncTheme {
        LandingPage()
    }
}
