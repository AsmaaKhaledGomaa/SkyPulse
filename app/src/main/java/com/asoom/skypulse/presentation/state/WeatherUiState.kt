package com.asoom.skypulse.presentation.state

import com.asoom.skypulse.domain.model.WeatherForecastResponse


sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(
        val data: WeatherForecastResponse,
        val cityName: String? = null
    ) : WeatherUiState()

    data class Error(val message: String) : WeatherUiState()
}
