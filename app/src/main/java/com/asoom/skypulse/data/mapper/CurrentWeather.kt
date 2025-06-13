package com.asoom.skypulse.data.mapper

import com.asoom.skypulse.data.remote.dto.CurrentWeatherDto
import com.asoom.skypulse.data.remote.dto.CurrentWeatherUnitsDto
import com.asoom.skypulse.domain.model.CurrentWeather
import com.asoom.skypulse.domain.model.CurrentWeatherUnits

fun CurrentWeatherDto.toDomain(): CurrentWeather =
    CurrentWeather(
        temperature = this.temperature ?: 0.0,
        apparentTemperature = this.apparentTemperature ?: 0.0,
        isDay = this.isDay ?: 0,
        humidity = this.humidity ?: 0,
        windSpeed = this.windSpeed ?: 0.0,
        precipitationProbability = this.precipitationProbability ?: 0,
        weatherCode = this.weatherCode ?: 0,
        surfacePressure = this.surfacePressure ?: 0.0,
        uvIndex = this.uvIndex ?: 0.0,
        pressureMsl = this.pressureMsl
    )


fun CurrentWeatherUnitsDto.toDomain(): CurrentWeatherUnits =
    CurrentWeatherUnits(
        temperature = this.temperature,
        apparentTemperature = this.apparentTemperature,
        humidity = this.humidity,
        windSpeed = this.windSpeed,
        precipitationProbability = this.precipitationProbability,
        weatherCode = this.weatherCode,
        surfacePressure = this.surfacePressure,
        uvIndex = this.uvIndex

    )