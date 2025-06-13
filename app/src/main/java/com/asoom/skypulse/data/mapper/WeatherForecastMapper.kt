package com.asoom.skypulse.data.mapper

import com.asoom.skypulse.data.remote.dto.WeatherForecastResponseDto
import com.asoom.skypulse.domain.model.WeatherForecastResponse
import com.asoom.skypulse.domain.model.CurrentWeather
import com.asoom.skypulse.domain.model.CurrentWeatherUnits
import com.asoom.skypulse.domain.model.HourlyWeather
import com.asoom.skypulse.domain.model.HourlyWeatherUnits
import com.asoom.skypulse.domain.model.DailyWeather
import com.asoom.skypulse.domain.model.DailyWeatherUnits
import com.asoom.skypulse.data.remote.dto.CurrentWeatherDto
import com.asoom.skypulse.data.remote.dto.CurrentWeatherUnitsDto
import com.asoom.skypulse.data.remote.dto.HourlyWeatherDto
import com.asoom.skypulse.data.remote.dto.HourlyWeatherUnitsDto
import com.asoom.skypulse.data.remote.dto.DailyWeatherDto
import com.asoom.skypulse.data.remote.dto.DailyWeatherUnitsDto

fun WeatherForecastResponseDto.toDomain(): WeatherForecastResponse =
    WeatherForecastResponse(
        latitude = this.latitude,
        longitude = this.longitude,
        currentWeather = this.currentWeatherDto?.toDomain() ?: CurrentWeather(
            temperature = 0.0,
            apparentTemperature = 0.0,
            isDay = 0,
            humidity = 0,
            windSpeed = 0.0,
            precipitationProbability = 0,
            weatherCode = 0,
            surfacePressure = 0.0,
            uvIndex = 0.0,
            pressureMsl = null
        ),
        currentWeatherUnits = this.currentWeatherUnitsDto?.toDomain() ?: CurrentWeatherUnits(
            temperature = "",
            apparentTemperature = "",
            humidity = "",
            windSpeed = "",
            precipitationProbability = "",
            weatherCode = "",
            surfacePressure = "",
            uvIndex = "",
        ),
        hourly = this.hourlyWeatherDto?.toDomain() ?: HourlyWeather(
            time = emptyList(),
            temperature = emptyList(),
            weatherCode = emptyList()
        ),
        hourlyUnits = this.hourlyWeatherUnitsDto?.toDomain() ?: HourlyWeatherUnits(
            time = "",
            temperature = "",
            weatherCode = ""
        ),
        daily = this.dailyWeatherDto?.toDomain() ?: DailyWeather(
            time = emptyList(),
            weatherCode = emptyList(),
            maxTemp = emptyList(),
            minTemp = emptyList()
        ),
        dailyUnits = this.dailyWeatherUnitsDto?.toDomain() ?: DailyWeatherUnits(
            time = "",
            weatherCode = "",
            maxTemp = "",
            minTemp = ""
        )
    )
