package com.example.aidsync.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.aidsync.data.AppDatabase
import com.example.aidsync.data.entities.User
import com.example.aidsync.data.repositories.UserRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileManagementPage(navController: NavController) {
    // Access the Context using LocalContext
    val context = LocalContext.current

    // Initialize the repository
    val database = AppDatabase.getDatabase(context)
    val userDao = database.userDao()
    val userRepository = UserRepository(userDao)

    // State variables to hold user details
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }


    // Coroutine scope for background operations
    val coroutineScope = rememberCoroutineScope()

    // Load user details
    LaunchedEffect(Unit) {
        val user = userRepository.getUserById(1) // Fetch user with ID 1
        user?.let {
            name = it.name
            email = it.email
        }
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
                // Name Field
                Text("Name", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                // Email Field
                Text("Email", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = email,
                    onValueChange = { /* No changes allowed for email */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    enabled = false // Email is not editable
                )



                // Save Button
                Button(
                    onClick = {
                        coroutineScope.launch {
                            userRepository.updateUser(
                                User(
                                    id = 1, // Replace with actual user ID
                                    name = name,
                                    email = email,
                                    password = "" // Placeholder for password
                                )
                            )
                            navController.popBackStack() // Navigate back after saving
                        }
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
