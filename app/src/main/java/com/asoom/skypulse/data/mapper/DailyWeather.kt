package com.asoom.skypulse.data.mapper

import com.asoom.skypulse.data.remote.dto.DailyWeatherDto
import com.asoom.skypulse.data.remote.dto.DailyWeatherUnitsDto
import com.asoom.skypulse.domain.model.DailyWeather
import com.asoom.skypulse.domain.model.DailyWeatherUnits

fun DailyWeatherDto.toDomain(): DailyWeather =
    DailyWeather(
        time = this.time,
        weatherCode = this.weatherCode,
        maxTemp = this.maxTemp,
        minTemp = this.minTemp
    )


fun DailyWeatherUnitsDto.toDomain(): DailyWeatherUnits =
    DailyWeatherUnits(
        time = this.time,
        weatherCode = this.weatherCode,
        maxTemp = this.maxTemp,
        minTemp = this.minTemp
    )