package com.example.aidsync.ui.patients

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmationScreen(onTrackClick: () -> Unit, estimatedTime: Int = 4) {
    var trackOnMap by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Thank you,", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("don't worry the ambulance is on the way")

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
        ) {
            Text("$estimatedTime min", fontSize = 32.sp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Checkbox(
                checked = trackOnMap,
                onCheckedChange = { trackOnMap = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Track the ambulance on map")
        }

        Button(onClick = onTrackClick, modifier = Modifier.fillMaxWidth(0.5f)) {
            Text("Track")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationScreenPreview() {
    ConfirmationScreen(onTrackClick = {})
}

