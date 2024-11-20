package com.example.aidsync.ui.firstaid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstAidTopicsPage(navController: NavController, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "First Aid Topics", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF111827)) // Dark top bar
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF111827)) // Dark background
        ) {
            item {
                Text(
                    text = "Select a Topic",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green, // Accent green
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                GridLayout(
                    topics = listOf(
                        "CPR", "Bleeding", "Burns", "Choking", "Poisons", "Wound Care",
                        "Allergic Reaction", "Asthma", "Anaphylaxis", "Diabetics",
                        "Drug Overdose", "Eye Injury", "Electric Shock", "Fractures",
                        "Frostbite", "Head Injury", "Heart Condition", "Nosebleed",
                        "Seizure", "Shock", "Spinal Injury", "Sprains & Strains",
                        "Stroke", "Tick Bites"
                    ),
                    onTopicClick = { topic ->
                        // Navigate to the FirstAidDetailsScreen with the selected topic
                        navController.navigate("firstAidDetails/$topic")
                    }
                )
            }
        }
    }
}

@Composable
fun GridLayout(topics: List<String>, onTopicClick: (String) -> Unit) {
    Column {
        topics.chunked(3).forEach { rowTopics ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowTopics.forEach { topic ->
                    FirstAidButton(label = topic, onClick = { onTopicClick(topic) })
                }
            }
        }
    }
}

@Composable
fun FirstAidButton(label: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1F2937)) // Dark card background
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Placeholder for icons (add actual icons if available)
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color.Red, shape = RoundedCornerShape(50)) // Red accent
        )
        Text(
            text = label,
            color = Color.White, // White text for visibility
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FirstAidTopicsPreview() {
    // Create a mock NavController for preview
    FirstAidTopicsPage(navController = NavController(LocalContext.current), onBackClick = {})
}
