package com.asoom.skypulse.data.mapper

import com.asoom.skypulse.data.remote.dto.HourlyWeatherDto
import com.asoom.skypulse.data.remote.dto.HourlyWeatherUnitsDto
import com.asoom.skypulse.domain.model.HourlyWeather
import com.asoom.skypulse.domain.model.HourlyWeatherUnits

fun HourlyWeatherDto.toDomain(): HourlyWeather =
    HourlyWeather(
        time = this.time,
        temperature = this.temperature,
        weatherCode = this.weatherCode
    )


fun HourlyWeatherUnitsDto.toDomain(): HourlyWeatherUnits =
    HourlyWeatherUnits(
        time = this.time,
        temperature = this.temperature,
        weatherCode = this.weatherCode
    )