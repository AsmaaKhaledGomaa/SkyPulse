package com.asoom.skypulse.domain.usecase

import com.asoom.skypulse.data.utils.DataState
import com.asoom.skypulse.domain.model.WeatherForecastResponse
import com.asoom.skypulse.domain.repo.WeatherForecastRepo
import kotlinx.coroutines.flow.Flow

class GetWeatherUseCase(private val weatherForecastRepo: WeatherForecastRepo) {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Flow<DataState<WeatherForecastResponse>> =
        weatherForecastRepo.getWeatherForecast(latitude, longitude)
}