package com.example.aidsync.ui.patienttracker

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aidsync.ui.theme.AidSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientCareTrackerScreen(viewModel: PatientCareTrackerViewModel = viewModel()) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val patientDetails = viewModel.patientDetails

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Patient Care Tracker", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF111827), // Dark background for TopAppBar
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Handle adding new details
                viewModel.addPatientDetail(PatientDetail("Heart Rate", "70 bpm"))
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFF111827)) // Dark background
            ) {
                item {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Display different sections based on selectedTab
                        when (selectedTab) {
                            0 -> VitalSignsSection(patientDetails)
                            1 -> MedicationsSection(patientDetails)
                            2 -> DietaryIntakeSection(patientDetails)
                            3 -> FluidBalanceSection()
                        }
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Vital Signs") },
                    label = { Text("Vital Signs", color = Color.White) },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Face, contentDescription = "Medications") },
                    label = { Text("Medications", color = Color.White) },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Dietary") },
                    label = { Text("Dietary", color = Color.White) },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Edit, contentDescription = "Fluid Balance") },
                    label = { Text("Fluid Balance", color = Color.White) },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
            }
        }
    )
}

@Composable
fun VitalSignsSection(patientDetails: List<PatientDetail>) {
    PatientCard(
        title = "Vital Signs",
        content = {
            for (detail in patientDetails.filter { it.title == "Heart Rate" }) {
                Text("${detail.title}: ${detail.description}", color = Color.White)
            }
        }
    )
}

@Composable
fun MedicationsSection(patientDetails: List<PatientDetail>) {
    PatientCard(
        title = "Medications",
        content = {
            for (detail in patientDetails.filter { it.title == "Medication" }) {
                Text("${detail.title}: ${detail.description}", color = Color.White)
            }
        }
    )
}

@Composable
fun DietaryIntakeSection(patientDetails: List<PatientDetail>) {
    PatientCard(
        title = "Dietary Intake",
        content = {
            for (detail in patientDetails.filter { it.title == "Dietary Intake" }) {
                Text("${detail.title}: ${detail.description}", color = Color.White)
            }
        }
    )
}

@Composable
fun FluidBalanceSection() {
    PatientCard(
        title = "Fluid Balance",
        content = { FluidBalancePieChart() }
    )
}

@Composable
fun PatientCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F2937)) // Dark card background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Green // Green accent color for the title
            )
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
fun FluidBalancePieChart() {
    val slices = listOf(
        "Water" to 60f,
        "Other Fluids" to 40f
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val total = slices.sumOf { it.second.toDouble() }.toFloat()
            var startAngle = -90f

            slices.forEach { (label, value) ->
                val sweepAngle = (value / total) * 360f
                drawArc(
                    color = when (label) {
                        "Water" -> Color(0xFF4CAF50) // Green for Water
                        "Other Fluids" -> Color(0xFF2196F3) // Blue for Other Fluids
                        else -> Color.Gray
                    },
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    size = size.copy(minOf(size.width, size.height), minOf(size.width, size.height)),
                    style = Fill
                )
                startAngle += sweepAngle
            }
        }

        Text(
            text = "Fluid Balance",
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PatientCareTrackerScreenPreview() {
    AidSyncTheme {
        PatientCareTrackerScreen()
    }
}
