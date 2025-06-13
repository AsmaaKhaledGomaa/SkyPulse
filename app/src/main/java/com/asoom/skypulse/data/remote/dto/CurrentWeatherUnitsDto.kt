package com.asoom.skypulse.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherUnitsDto(
    val time: String,
    @SerialName("interval")
    val interval: String,
    @SerialName("temperature_2m")
    val temperature: String,
    @SerialName("apparent_temperature")
    val apparentTemperature: String,
    @SerialName("is_day")
    val isDay: String,
    @SerialName("relative_humidity_2m")
    val humidity: String,
    @SerialName("wind_speed_10m")
    val windSpeed: String,
    @SerialName("precipitation_probability")
    val precipitationProbability: String,
    val rain: String?,
    @SerialName("weather_code")
    val weatherCode: String,
    @SerialName("surface_pressure")
    val surfacePressure: String,
    @SerialName("uv_index")
    val uvIndex: String,
    @SerialName("pressure_msl")
    val pressureMsl: String
)
