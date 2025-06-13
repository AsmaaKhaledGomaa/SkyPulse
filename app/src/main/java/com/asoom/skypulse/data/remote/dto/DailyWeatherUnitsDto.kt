package com.asoom.skypulse.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherUnitsDto(
    @SerialName("time")
    val time: String,
    @SerialName("weather_code")
    val weatherCode: String,
    @SerialName("temperature_2m_max")
    val maxTemp: String,
    @SerialName("temperature_2m_min")
    val minTemp: String
)