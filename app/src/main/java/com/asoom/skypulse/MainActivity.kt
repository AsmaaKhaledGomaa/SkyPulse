package com.asoom.skypulse

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.asoom.skypulse.presentation.screen.WeatherScreen
import com.asoom.skypulse.presentation.theme.SkyPulseTheme
import com.asoom.skypulse.presentation.screen.LocationScreen
import com.asoom.skypulse.presentation.viewModel.LocationViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkyPulseTheme {
                val navController = rememberNavController()
                val locationViewModel: LocationViewModel = koinViewModel()
                val locationUiState by locationViewModel.uiState.collectAsState()

                LaunchedEffect(locationUiState.shouldNavigateToWeather) {
                    if (locationUiState.shouldNavigateToWeather && locationUiState.latitude != null && locationUiState.longitude != null) {
                        navController.navigate("weather_screen/${locationUiState.latitude}/${locationUiState.longitude}") {
                            popUpTo("location_screen") { inclusive = true }
                        }
                    }
                }

                NavHost(navController = navController, startDestination = "location_screen") {
                    composable("location_screen") {
                        LocationScreen(navController = navController)
                    }
                    composable(
                        "weather_screen/{latitude}/{longitude}/{cityName}",
                        arguments = listOf(
                            navArgument("latitude") { type = NavType.StringType },
                            navArgument("longitude") { type = NavType.StringType },
                            navArgument("cityName") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val latitude = backStackEntry.arguments?.getString("latitude")?.toDoubleOrNull()
                        val longitude = backStackEntry.arguments?.getString("longitude")?.toDoubleOrNull()
                        val cityName = backStackEntry.arguments?.getString("cityName")
                        if (latitude != null && longitude != null && cityName != null) {
                            WeatherScreen(latitude = latitude, longitude = longitude, cityName = cityName, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
