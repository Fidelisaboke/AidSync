package com.example.aidsync.ui.patients

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aidsync.R
import com.example.aidsync.ui.theme.AidSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage(
    onCasualtyReportClick: () -> Unit = {},
    onPatientTrackerClick: () -> Unit = {},
    onFirstAidTopicsClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onEmergencyHotlinesClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome", color = Color.White, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = onEmergencyHotlinesClick) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Emergency Hotlines",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF111827) // Dark theme for TopAppBar
                )
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFF111827)) // Dark background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // App Logo
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "AidSync Logo",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(bottom = 16.dp)
                    )
                    // Title
                    Text(
                        text = "Manage First Aid Effortlessly",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green, // Accent green color
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    // Subtitle
                    Text(
                        text = "Navigate through key features to assist in providing timely and effective first aid.",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Feature buttons
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        FeatureButton(
                            label = "Casualty Report",
                            icon = Icons.Default.Description,
                            onClick = onCasualtyReportClick,
                            backgroundColor = Color(0xFF2E7D32) // Darker green
                        )
                        FeatureButton(
                            label = "Patient Tracker",
                            icon = Icons.Default.MedicalServices,
                            onClick = onPatientTrackerClick,
                            backgroundColor = Color(0xFF1B5E20) // Darker, muted green
                        )
                        FeatureButton(
                            label = "First Aid Topics",
                            icon = Icons.Default.Info,
                            onClick = onFirstAidTopicsClick,
                            backgroundColor = Color(0xFF145A32) // Even darker muted green
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun FeatureButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingPagePreview() {
    AidSyncTheme {
        LandingPage()
    }
}
