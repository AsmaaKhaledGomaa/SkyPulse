package com.asoom.skypulse.presentation.screen

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asoom.skypulse.presentation.viewModel.LocationViewModel
import com.google.accompanist.permissions.*
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationScreen(
    navController: NavController,
    viewModel: LocationViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val locationPermission = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(locationPermission.status) {
        when (locationPermission.status) {
            is PermissionStatus.Granted -> viewModel.onPermissionResult(true)
            is PermissionStatus.Denied -> viewModel.onPermissionResult(false)
        }
    }

    LaunchedEffect(uiState.shouldNavigateToWeather) {
        if (uiState.shouldNavigateToWeather && uiState.latitude != null && uiState.longitude != null) {
            val cityName = uiState.cityName ?: "Unknown"
            navController.navigate("weather_screen/${uiState.latitude}/${uiState.longitude}/$cityName") {
                popUpTo("location_screen") { inclusive = true }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {

            when {
                !uiState.isPermissionGranted -> {
                    Text(
                        text = "📍 No location access",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "We need your location to show weather data.\nPlease grant access.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = { locationPermission.launchPermissionRequest() },
                        enabled = !uiState.isLoading
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            Text("Allow Location Access")
                        }
                    }
                }

                uiState.isLoading -> {
                    CircularProgressIndicator()
                    Text("Fetching location...", style = MaterialTheme.typography.bodyMedium)
                }

                uiState.latitude != null && uiState.longitude != null -> {
                    Text(
                        text = "✅ Location Found",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Latitude: ${uiState.latitude}\nLongitude: ${uiState.longitude}",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Button(onClick = { /* Navigate to weather screen */ }) {
                        Text("Continue")
                    }
                }
            }

            uiState.error?.let { error ->
                Text(
                    text = "❌ $error",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
                Button(onClick = { viewModel.onPermissionResult(true) }) {
                    Text("Retry")
                }
            }
        }
    }
}

