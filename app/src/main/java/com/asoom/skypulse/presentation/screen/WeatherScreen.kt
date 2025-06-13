package com.asoom.skypulse.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.asoom.skypulse.presentation.state.WeatherUiState
import com.asoom.skypulse.presentation.viewModel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.NavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel = koinViewModel(),
    latitude: Double,
    longitude: Double,
    cityName: String,
    navController: NavController
) {
    val weatherState by weatherViewModel.weatherState.collectAsState()

    LaunchedEffect(latitude, longitude, cityName) {
        weatherViewModel.getWeather(latitude = latitude, longitude = longitude, cityName = cityName)
    }

    WeatherScreenContent(weatherUiState = weatherState)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WeatherScreenContentPreview() {
    WeatherScreenContent(WeatherUiState.Loading)
}