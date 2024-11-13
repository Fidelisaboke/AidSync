package com.example.aidsync.ui.patients

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmergencySelectionScreen(onReportClick: () -> Unit) {
    val options = listOf("Heart Attack", "Allergy Attack", "Extreme Pain", "Serious Accident", "Unconsciousness", "Other")
    var selectedOption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Please, press what you are experiencing to call ambulance", textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        options.chunked(2).forEach { rowOptions ->
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                rowOptions.forEach { option ->
                    Surface(
                        shape = CircleShape,
                        color = if (selectedOption == option) MaterialTheme.colorScheme.primary else Color.LightGray,
                        modifier = Modifier
                            .size(100.dp)
                            .clickable { selectedOption = option }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(option, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = onReportClick,
            enabled = selectedOption.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Report")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmergencySelectionScreenPreview() {
    EmergencySelectionScreen(onReportClick = {})
}

