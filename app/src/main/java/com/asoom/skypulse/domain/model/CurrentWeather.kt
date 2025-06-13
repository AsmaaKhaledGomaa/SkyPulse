package com.asoom.skypulse.domain.model

data class CurrentWeather(
    val temperature: Double,
    val apparentTemperature: Double,
    val isDay: Int,
    val humidity: Int,
    val windSpeed: Double,
    val precipitationProbability: Int,
    val weatherCode: Int,
    val surfacePressure: Double,
    val uvIndex: Double,
    val pressureMsl: Double? = null
)

