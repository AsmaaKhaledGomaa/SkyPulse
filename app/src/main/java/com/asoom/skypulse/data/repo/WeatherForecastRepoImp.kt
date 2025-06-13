package com.asoom.skypulse.data.repo

import com.asoom.skypulse.data.utils.DataState
import com.asoom.skypulse.data.datasource.WeatherForecastDataSource
import com.asoom.skypulse.data.mapper.toDomain
import com.asoom.skypulse.data.remote.dto.WeatherForecastResponseDto
import com.asoom.skypulse.domain.model.WeatherForecastResponse
import com.asoom.skypulse.domain.repo.WeatherForecastRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class WeatherForecastRepoImp(
    private val weatherForecastDataSource: WeatherForecastDataSource
) : WeatherForecastRepo {

    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Flow<DataState<WeatherForecastResponse>> = flow {
        emit(DataState.Loading)

        val response = weatherForecastDataSource.getWeatherForecast(latitude, longitude)
            .map { dataState ->
                when (dataState) {
                    is DataState.Loading -> DataState.Loading
                    is DataState.Success -> DataState.Success(dataState.data.toDomain())
                    is DataState.Error -> DataState.Error(dataState.error)
                }
            }

        emitAll(response)
    }.catch { e ->
        emit(DataState.Error(e))
    }
}