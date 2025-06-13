package com.asoom.skypulse.domain.model

data class WeatherForecastResponse(
    val latitude: Double,
    val longitude: Double,
    val currentWeather: CurrentWeather?,
    val currentWeatherUnits: CurrentWeatherUnits?,
    val hourly: HourlyWeather?,
    val hourlyUnits: HourlyWeatherUnits?,
    val daily: DailyWeather?,
    val dailyUnits: DailyWeatherUnits?,
//    val generationTime: Double,
//    val utcOffsetSeconds: Int,
//    val timezone: String,
//    val timezoneAbbreviation: String,
//    val elevation: Double,
)
