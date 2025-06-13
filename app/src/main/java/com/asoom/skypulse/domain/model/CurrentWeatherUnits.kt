package com.asoom.skypulse.domain.model

data class CurrentWeatherUnits(
    val temperature: String,
    val apparentTemperature: String,
    val humidity: String,
    val windSpeed: String,
    val precipitationProbability: String,
    val weatherCode: String,
    val surfacePressure: String,
    val uvIndex: String,
)
