package com.example.aidsync.ui.casualtyreport

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aidsync.data.entities.CasualtyReport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasualtyReportScreen(viewModel: CasualtyReportViewModel = viewModel()) {
    val allReports by viewModel.allReports.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Casualty Reports") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Report")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            // Make the list of casualty reports scrollable
            CasualtyReportList(reports = allReports)

            if (showDialog) {
                AddCasualtyReportDialog(
                    onAddReport = { report ->
                        viewModel.insertReport(report)
                        showDialog = false
                    },
                    onDismiss = { showDialog = false }
                )
            }
        }
    }
}

@Composable
fun CasualtyReportList(reports: List<CasualtyReport>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reports.size) { index ->
            CasualtyReportItem(report = reports[index])
        }
    }
}

@Composable
fun CasualtyReportItem(report: CasualtyReport) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp) // Rounded corners for the card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${report.casualtyName}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Age: ${report.casualtyAge}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Incident: ${report.incidentDescription}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Location: ${report.incidentLocation}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Date: ${report.incidentDate} | Time: ${report.incidentTime}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun AddCasualtyReportDialog(
    onAddReport: (CasualtyReport) -> Unit,
    onDismiss: () -> Unit
) {
    var casualtyName by remember { mutableStateOf(TextFieldValue("")) }
    var casualtyAge by remember { mutableStateOf(TextFieldValue("")) }
    var contactInfo by remember { mutableStateOf(TextFieldValue("")) }
    var incidentDescription by remember { mutableStateOf(TextFieldValue("")) }
    var incidentLocation by remember { mutableStateOf(TextFieldValue("")) }
    var incidentDate by remember { mutableStateOf(TextFieldValue("")) }
    var incidentTime by remember { mutableStateOf(TextFieldValue("")) }
    var bodyPartsInjured by remember { mutableStateOf(TextFieldValue("")) }
    var stateOfResponsiveness by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val report = CasualtyReport(
                    casualtyName = casualtyName.text,
                    casualtyAge = casualtyAge.text.toIntOrNull() ?: 0,
                    contactInfo = contactInfo.text,
                    incidentDescription = incidentDescription.text,
                    incidentLocation = incidentLocation.text,
                    incidentDate = incidentDate.text,
                    incidentTime = incidentTime.text,
                    bodyPartsInjured = bodyPartsInjured.text,
                    stateOfResponsiveness = stateOfResponsiveness.text,
                    gender = gender.text,
                    recordedBy = 123 // Temporary fix -> replace with actual value
                )
                onAddReport(report)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add Casualty Report") },
        text = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()) // Enable scrolling for the dialog content
                    .padding(16.dp)
            ) {
                CasualtyReportField("Name", casualtyName) { casualtyName = it }
                CasualtyReportField("Age", casualtyAge) { casualtyAge = it }
                CasualtyReportField("Contact Info", contactInfo) { contactInfo = it }
                CasualtyReportField("Description", incidentDescription) { incidentDescription = it }
                CasualtyReportField("Location", incidentLocation) { incidentLocation = it }
                CasualtyReportField("Date", incidentDate) { incidentDate = it }
                CasualtyReportField("Time", incidentTime) { incidentTime = it }
                CasualtyReportField("Body Parts Injured", bodyPartsInjured) { bodyPartsInjured = it }
                CasualtyReportField("State of Responsiveness", stateOfResponsiveness) { stateOfResponsiveness = it }
                CasualtyReportField("Gender", gender) { gender = it }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasualtyReportField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp), // Rounded corners for the text fields
        singleLine = true,
        colors = OutlinedTextFieldDefaults. colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Gray
        )
    )
}
