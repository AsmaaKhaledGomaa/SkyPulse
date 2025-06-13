package com.asoom.skypulse.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponseDto(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("current")
    val currentWeatherDto: CurrentWeatherDto? = null,
    @SerialName("current_units")
    val currentWeatherUnitsDto: CurrentWeatherUnitsDto? = null,
    @SerialName("hourly")
    val hourlyWeatherDto: HourlyWeatherDto? = null,
    @SerialName("hourly_units")
    val hourlyWeatherUnitsDto: HourlyWeatherUnitsDto? = null,
    @SerialName("daily")
    val dailyWeatherDto: DailyWeatherDto? = null,
    @SerialName("daily_units")
    val dailyWeatherUnitsDto: DailyWeatherUnitsDto? = null,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("elevation")
    val elevation: Double,
)
