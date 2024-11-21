package com.example.aidsync.ui.firstaid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF111827))
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF111827))
        ) {
            item {
                Text(
                    text = "Select a Topic",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green,
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
    val icon = when (label) {
        "CPR" -> Icons.Default.Favorite
        "Bleeding" -> Icons.Default.Bloodtype
        "Burns" -> Icons.Default.LocalFireDepartment
        "Choking" -> Icons.Default.Masks
        "Poisons" -> Icons.Default.Science
        "Wound Care" -> Icons.Default.Healing
        "Allergic Reaction" -> Icons.Default.Coronavirus
        "Asthma" -> Icons.Default.Air
        "Anaphylaxis" -> Icons.Default.MedicalServices
        "Diabetics" -> Icons.Default.Medication
        "Eye Injury" -> Icons.Default.Visibility
        "Electric Shock" -> Icons.Default.Bolt
        "Fractures" -> Icons.Default.Accessibility
        "Frostbite" -> Icons.Default.AcUnit
        "Head Injury" -> Icons.Default.Psychology
        "Heart Condition" -> Icons.Default.HeartBroken
        "Nosebleed" -> Icons.Default.WaterDrop
        "Seizure" -> Icons.Default.Bolt
        "Shock" -> Icons.Default.ElectricBolt
        "Spinal Injury" -> Icons.Default.AccessibilityNew
        "Sprains & Strains" -> Icons.Default.FitnessCenter
        "Stroke" -> Icons.Default.MedicalInformation
        "Tick Bites" -> Icons.Default.BugReport
        else -> Icons.Default.Info
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1F2937))
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Green,
            modifier = Modifier.size(36.dp)
        )
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FirstAidTopicsPreview() {
    FirstAidTopicsPage(
        navController = rememberNavController(),
        onBackClick = {}
    )
}
