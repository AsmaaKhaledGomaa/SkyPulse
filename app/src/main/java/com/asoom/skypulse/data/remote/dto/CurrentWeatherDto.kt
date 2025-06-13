package com.asoom.skypulse.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("time")
    val time: String,
    @SerialName("interval")
    val interval: Int,
    @SerialName("temperature_2m")
    val temperature: Double?,
    @SerialName("apparent_temperature")
    val apparentTemperature: Double?,
    @SerialName("is_day")
    val isDay: Int?,
    @SerialName("relative_humidity_2m")
    val humidity: Int?,
    @SerialName("wind_speed_10m")
    val windSpeed: Double?,
    @SerialName("precipitation_probability")
    val precipitationProbability: Int?,
    val rain: Double?,
    @SerialName("weather_code")
    val weatherCode: Int?,
    @SerialName("surface_pressure")
    val surfacePressure: Double?,
    @SerialName("uv_index")
    val uvIndex: Double?,
    @SerialName("pressure_msl")
    val pressureMsl: Double?
)