package com.example.aidsync.ui.authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aidsync.R
import com.example.aidsync.data.entities.User
import com.example.aidsync.ui.theme.AidSyncTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var registerError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111827))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(80.dp)
                .padding(bottom = 24.dp)
        )

        // Title
        Text(
            text = "Create your Account",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Name Field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color.Gray) },
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(icon, contentDescription = "Toggle Password Visibility")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password", color = Color.Gray) },
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    validateAndRegister(
                        name = name,
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword,
                        context = context,
                        scope = scope,
                        viewModel = viewModel,
                        onRegisterSuccess = onRegisterSuccess,
                        onRegisterError = { registerError = true }
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        Button(
            onClick = {
                validateAndRegister(
                    name = name,
                    email = email,
                    password = password,
                    confirmPassword = confirmPassword,
                    context = context,
                    scope = scope,
                    viewModel = viewModel,
                    onRegisterSuccess = onRegisterSuccess,
                    onRegisterError = { registerError = true }
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Register", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { onNavigateToLogin() }
        ) {
            Text("Already have an account? Login", color = Color.Green, fontSize = 14.sp)
        }

        if (registerError) {
            Text(
                text = "Please check your inputs and try again.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

/**
 * Validates and registers a new user.
 */
private fun validateAndRegister(
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    viewModel: RegisterViewModel,
    scope: CoroutineScope,
    context: android.content.Context,
    onRegisterSuccess: () -> Unit,
    onRegisterError: () -> Unit
) {
    if (email.isNotBlank() && password == confirmPassword && password.isNotBlank()) {
        scope.launch {
            val user = User(name = name, email = email, password = password)
            val registerSuccess = viewModel.register(user)

            if (registerSuccess) {
                onRegisterSuccess()
                Toast.makeText(
                    context,
                    "Registration successful!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                onRegisterError()
                Toast.makeText(
                    context,
                    "Registration failed. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    } else {
        onRegisterError()
        Toast.makeText(
            context,
            "Registration failed. Please check your inputs and try again.",
            Toast.LENGTH_SHORT
        ).show()
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    AidSyncTheme {
        RegisterScreen(
            onRegisterSuccess = { println("Registration successful!") },
            onNavigateToLogin = { println("Navigating to login screen") }
        )
    }
}
