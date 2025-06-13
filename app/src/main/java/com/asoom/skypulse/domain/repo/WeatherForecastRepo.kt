package com.asoom.skypulse.domain.repo

import com.asoom.skypulse.data.utils.DataState
import com.asoom.skypulse.domain.model.WeatherForecastResponse
import kotlinx.coroutines.flow.Flow


interface WeatherForecastRepo {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Flow<DataState<WeatherForecastResponse>>
}