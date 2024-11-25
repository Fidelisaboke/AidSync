package com.example.aidsync.ui.casualtyreport

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aidsync.data.entities.CasualtyReport
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasualtyReportScreen(
    navController: NavController,
    viewModel: CasualtyReportViewModel = viewModel()
) {
    val allReports by viewModel.allReports.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Casualty Reports") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Report")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            CasualtyReportList(
                reports = allReports,
                onReportClick = { report ->
                    navController.navigate("casualtyReportDetails/${report.id}")
                }
            )

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
fun CasualtyReportList(
    reports: List<CasualtyReport>,
    onReportClick: (CasualtyReport) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reports.size) { index ->
            CasualtyReportItem(
                report = reports[index],
                onClick = { onReportClick(reports[index]) }
            )
        }
    }
}

@Composable
fun CasualtyReportItem(
    report: CasualtyReport,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${report.casualtyName}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Age: ${report.casualtyAge}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Incident: ${report.incidentDescription}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
    var incidentDate by remember { mutableStateOf("") }
    var incidentTime by remember { mutableStateOf(TextFieldValue("")) }
    var bodyPartsInjured by remember { mutableStateOf(TextFieldValue("")) }
    var stateOfResponsiveness by remember { mutableStateOf(TextFieldValue("")) }
    var selectedGender by remember { mutableStateOf("") }
    val genderOptions = listOf("Male", "Female", "Other")

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (casualtyName.text.isNotBlank() && incidentDescription.text.isNotBlank()) {
                    val report = CasualtyReport(
                        casualtyName = casualtyName.text,
                        casualtyAge = casualtyAge.text.toIntOrNull() ?: 0,
                        contactInfo = contactInfo.text,
                        incidentDescription = incidentDescription.text,
                        incidentLocation = incidentLocation.text,
                        incidentDate = incidentDate,
                        incidentTime = incidentTime.text,
                        bodyPartsInjured = bodyPartsInjured.text,
                        stateOfResponsiveness = stateOfResponsiveness.text,
                        gender = selectedGender,
                        recordedBy = 1
                    )
                    onAddReport(report)
                }
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
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                CasualtyReportField("Name", casualtyName) { casualtyName = it }
                CasualtyReportField("Age", casualtyAge) { casualtyAge = it }
                CasualtyReportField("Contact Info", contactInfo) { contactInfo = it }
                CasualtyReportField("Description", incidentDescription) { incidentDescription = it }
                CasualtyReportField("Location", incidentLocation) { incidentLocation = it }

                DatePickerField("Date", incidentDate) { incidentDate = it }
                CasualtyReportField("Time", incidentTime) { incidentTime = it }
                CasualtyReportField("Body Parts Injured", bodyPartsInjured) { bodyPartsInjured = it }
                CasualtyReportField("State of Responsiveness", stateOfResponsiveness) { stateOfResponsiveness = it }
                GenderDropdown(selectedGender, genderOptions) { selectedGender = it }
            }
        }
    )
}

@Composable
fun DatePickerField(
    label: String,
    dateValue: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            val formattedDate = "$year-${month + 1}-$day"
            onDateSelected(formattedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = dateValue,
        onValueChange = {},
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { datePickerDialog.show() },
        readOnly = true, // Make the field read-only, so it only opens the dialog
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(Icons.Default.CalendarToday, contentDescription = "Pick Date")
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Gray
        )
    )
}


@Composable
fun GenderDropdown(
    selectedGender: String,
    options: List<String>,
    onGenderSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        OutlinedTextField(
            value = selectedGender,
            onValueChange = {},
            label = { Text("Gender") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Expand")
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onGenderSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
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
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Gray
        )
    )
}
