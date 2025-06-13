package com.asoom.skypulse.data.datasource

import com.asoom.skypulse.data.DataState
import com.asoom.skypulse.data.remote.WeatherApi
import com.asoom.skypulse.data.remote.dto.WeatherForecastResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherForecastDataSourceImp(
    private val weatherApi: WeatherApi
) : WeatherForecastDataSource {
    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Flow<DataState<WeatherForecastResponseDto>> = flow {
        emit(DataState.Loading)
        try {
            val response = weatherApi.getWeatherForecast(latitude, longitude)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}