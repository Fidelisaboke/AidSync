package com.example.aidsync.ui.patients

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aidsync.ui.theme.AidSyncTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileManagementPage(navController: NavController) {
    // Firebase Authentication instance
    val auth = FirebaseAuth.getInstance()
    val currentUser: FirebaseUser? = auth.currentUser

    // State variables to hold user profile details
    var name by remember { mutableStateOf(currentUser?.displayName ?: "Unknown User") }
    var email by remember { mutableStateOf(currentUser?.email ?: "unknown@example.com") }
    var phoneNumber by remember { mutableStateOf(currentUser?.phoneNumber ?: "Not Provided") }

    // Function to save the updated profile
    fun saveProfile() {
        // You can update the user's profile information in Firebase here
        currentUser?.let { user ->
            val profileUpdates = userProfileChangeRequest {
                displayName = name
                // Note: Email and phone number updates require separate Firebase methods
            }
            user.updateProfile(profileUpdates).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ProfileManagement", "User profile updated.")
                }
            }
        }

        // For this example, we'll just print to the console
        println("Profile saved!")
        println("Name: $name")
        println("Email: $email")
        println("Phone Number: $phoneNumber")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile Management", fontWeight = FontWeight.Bold) })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Profile Name
                Text("Name", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                // Email Address
                Text("Email", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    enabled = false // Email is typically not editable
                )

                // Phone Number
                Text("Phone Number", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    enabled = false // Phone number updates require a verification process
                )

                // Save Button
                Button(
                    onClick = {
                        saveProfile()
                        navController.popBackStack() // Navigate back to the previous page
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Save Changes")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileManagementPagePreview() {
    AidSyncTheme {
        ProfileManagementPage(navController = rememberNavController())
    }
}
