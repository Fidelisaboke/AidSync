package com.example.aidsync.ui.casualtyreport

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.text.input.TextFieldValue
import com.example.aidsync.data.entities.CasualtyReport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasualtyReportDetailsScreen(
    reportId: Int,
    navController: NavController,
    viewModel: CasualtyReportViewModel = viewModel()
) {
    val report by viewModel.getReportById(reportId).collectAsState(initial = null)

    if (report == null) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary)
        return
    }

    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Name: ${report!!.casualtyName}", style = MaterialTheme.typography.titleLarge)
            Text("Age: ${report!!.casualtyAge}", style = MaterialTheme.typography.bodyLarge)
            Text("Incident: ${report!!.incidentDescription}", style = MaterialTheme.typography.bodyLarge)
            Text("Location: ${report!!.incidentLocation}", style = MaterialTheme.typography.bodyLarge)
            Text("Date: ${report!!.incidentDate} | Time: ${report!!.incidentTime}", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    showEditDialog = true
                }) {
                    Text("Edit")
                }
                Button(
                    onClick = {
                        showDeleteConfirmationDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            }
        }
    }
    if (showEditDialog) {
        EditCasualtyReportDialog(
            report = report!!,
            onDismiss = { showEditDialog = false },
            onSave = { updatedReport ->
                viewModel.updateCasualtyReport(updatedReport)
                showEditDialog = false
            }
        )
    }

    // Show the DeleteConfirmationDialog when needed
    if (showDeleteConfirmationDialog) {
        DeleteConfirmationDialog(
            navController = navController,  // Pass the navController here
            showDialog = showDeleteConfirmationDialog,  // Pass the dialog state here
            onConfirmDelete = {
                if (report != null) {
                    viewModel.deleteReport(report!!) // Delete the report
                    showDeleteConfirmationDialog = false  // Hide the dialog after deletion
                    navController.navigateUp()  // Navigate back after deletion
                }
            },
            onDismiss = { showDeleteConfirmationDialog = false }  // Dismiss the dialog on "No"
        )
    }
}


@Composable
fun EditCasualtyReportDialog(
    report: CasualtyReport,
    onDismiss: () -> Unit,
    onSave: (CasualtyReport) -> Unit
) {
    var casualtyName by remember { mutableStateOf(TextFieldValue(report.casualtyName)) }
    var casualtyAge by remember { mutableStateOf(TextFieldValue(report.casualtyAge.toString())) }
    var incidentDescription by remember { mutableStateOf(TextFieldValue(report.incidentDescription)) }
    var incidentLocation by remember { mutableStateOf(TextFieldValue(report.incidentLocation)) }
    var incidentDate by remember { mutableStateOf(report.incidentDate) }
    var incidentTime by remember { mutableStateOf(TextFieldValue(report.incidentTime)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val updatedReport = report.copy(
                    casualtyName = casualtyName.text,
                    casualtyAge = casualtyAge.text.toIntOrNull() ?: 0,
                    incidentDescription = incidentDescription.text,
                    incidentLocation = incidentLocation.text,
                    incidentDate = incidentDate,
                    incidentTime = incidentTime.text
                )
                onSave(updatedReport)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Edit Report") },
        text = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                CasualtyReportField("Name", casualtyName) { casualtyName = it }
                CasualtyReportField("Age", casualtyAge) { casualtyAge = it }
                CasualtyReportField("Description", incidentDescription) { incidentDescription = it }
                CasualtyReportField("Location", incidentLocation) { incidentLocation = it }
                CasualtyReportField("Date", TextFieldValue(incidentDate)) { incidentDate = it.text }
                CasualtyReportField("Time", incidentTime) { incidentTime = it }
            }
        }
    )
}

@Composable
fun DeleteConfirmationDialog(
    navController: NavController,
    showDialog: Boolean,
    onConfirmDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    // Confirmation Dialog logic
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Delete Report") },
            text = { Text("Are you sure you want to delete this report?") },
            confirmButton = {
                TextButton(onClick = {
                    // Handle confirmation and call the delete function
                    onConfirmDelete()
                    navController.navigateUp() // Navigate back after confirmation
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("No")
                }
            }
        )
    }
}


