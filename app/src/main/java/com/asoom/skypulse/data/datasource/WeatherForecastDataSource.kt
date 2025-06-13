package com.asoom.skypulse.data.datasource

import com.asoom.skypulse.data.DataState
import com.asoom.skypulse.data.remote.dto.WeatherForecastResponseDto
import kotlinx.coroutines.flow.Flow

interface WeatherForecastDataSource {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Flow<DataState<WeatherForecastResponseDto>>
}