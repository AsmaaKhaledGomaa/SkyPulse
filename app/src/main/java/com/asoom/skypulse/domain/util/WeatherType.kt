package com.asoom.skypulse.domain.util

import androidx.annotation.DrawableRes
import com.asoom.skypulse.R

sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes val iconResDay: Int,
    @DrawableRes val iconResNight: Int
) {
    object ClearSky : WeatherType(
        weatherDescription = "Clear sky",
        iconResDay = R.drawable.clear_sky_1,
        iconResNight = R.drawable.clear_sky
    )

    object MainlyClear : WeatherType(
        weatherDescription = "Mainly clear",
        iconResDay = R.drawable.mainly_clear_1,
        iconResNight = R.drawable.mainly_clear
    )

    object PartlyCloudy : WeatherType(
        weatherDescription = "Partly cloudy",
        iconResDay = R.drawable.partly_cloudy_1,
        iconResNight = R.drawable.partly_cloudy
    )

    object Overcast : WeatherType(
        weatherDescription = "Overcast",
        iconResDay = R.drawable.overcast_1,
        iconResNight = R.drawable.overcast
    )

    object Fog : WeatherType(
        weatherDescription = "Fog",
        iconResDay = R.drawable.fog,
        iconResNight = R.drawable.fog
    )

    object DepositingRimeFog : WeatherType(
        weatherDescription = "Depositing rime fog",
        iconResDay = R.drawable.fog_1,
        iconResNight = R.drawable.fog
    )

    object LightDrizzle : WeatherType(
        weatherDescription = "Light drizzle",
        iconResDay = R.drawable.drizzle_light_1,
        iconResNight = R.drawable.drizzle_light
    )

    object ModerateDrizzle : WeatherType(
        weatherDescription = "Moderate drizzle",
        iconResDay = R.drawable.drizzle_moderate_1,
        iconResNight = R.drawable.drizzle_moderate_1
    )

    object DenseDrizzle : WeatherType(
        weatherDescription = "Dense drizzle",
        iconResDay = R.drawable.drizzle_intensity_1,
        iconResNight = R.drawable.drizzle_intensity
    )

    object LightFreezingDrizzle : WeatherType(
        weatherDescription = "Light freezing drizzle",
        iconResDay = R.drawable.drizzle_light_1,
        iconResNight = R.drawable.drizzle_light
    )

    object DenseFreezingDrizzle : WeatherType(
        weatherDescription = "Dense freezing drizzle",
        iconResDay = R.drawable.freezing_drizzle_light,
        iconResNight = R.drawable.freezing_drizzle_light
    )

    object SlightRain : WeatherType(
        weatherDescription = "Slight rain",
        iconResDay = R.drawable.rain_slight_1,
        iconResNight = R.drawable.rain_slight
    )

    object ModerateRain : WeatherType(
        weatherDescription = "Moderate rain",
        iconResDay = R.drawable.rain_moderate_1,
        iconResNight = R.drawable.rain_moderate
    )

    object HeavyRain : WeatherType(
        weatherDescription = "Heavy rain",
        iconResDay = R.drawable.snow_shower_heavy_1,
        iconResNight = R.drawable.snow_shower_heavy
    )

    object HeavyFreezingRain : WeatherType(
        weatherDescription = "Heavy freezing rain",
        iconResDay = R.drawable.freezing_heavy_1,
        iconResNight = R.drawable.freezing_heavy
    )

    object SlightSnowFall : WeatherType(
        weatherDescription = "Slight snow fall",
        iconResDay = R.drawable.snow_shower_slight_1,
        iconResNight = R.drawable.snow_shower_slight
    )

    object ModerateSnowFall : WeatherType(
        weatherDescription = "Moderate snow fall",
        iconResDay = R.drawable.snow_fall_moderate_1,
        iconResNight = R.drawable.snow_fall_moderate
    )

    object HeavySnowFall : WeatherType(
        weatherDescription = "Heavy snow fall",
        iconResDay = R.drawable.snow_shower_heavy_1,
        iconResNight = R.drawable.snow_shower_heavy
    )

    object SnowGrains : WeatherType(
        weatherDescription = "Snow grains",
        iconResDay = R.drawable.snow_grains_1,
        iconResNight = R.drawable.snow_grains
    )

    object LightRainShowers : WeatherType(
        weatherDescription = "Light rain showers",
        iconResDay = R.drawable.rain_shower_slight_1,
        iconResNight = R.drawable.rain_shower_slight
    )

    object ModerateRainShowers : WeatherType(
        weatherDescription = "Moderate rain showers",
        iconResDay = R.drawable.rain_shower_moderate_1,
        iconResNight = R.drawable.rain_shower_moderate
    )

    object ViolentRainShowers : WeatherType(
        weatherDescription = "Violent rain showers",
        iconResDay = R.drawable.rain_shower_violent_1,
        iconResNight = R.drawable.rain_shower_violent
    )

    object LightSnowShowers : WeatherType(
        weatherDescription = "Light snow showers",
        iconResDay = R.drawable.snow_shower_slight_1,
        iconResNight = R.drawable.snow_shower_slight
    )

    object ModerateSnowShowers : WeatherType(
        weatherDescription = "Moderate snow showers",
        iconResDay = R.drawable.rain_shower_moderate_1,
        iconResNight = R.drawable.rain_shower_moderate
    )

    object Thunderstorm : WeatherType(
        weatherDescription = "Thunderstorm",
        iconResDay = R.drawable.thunderstrom_slight_or_moderate_1,
        iconResNight = R.drawable.thunderstrom_slight_or_moderate
    )

    object SlightHailThunderstorm : WeatherType(
        weatherDescription = "Thunderstorm with slight hail",
        iconResDay = R.drawable.thunderstrom_with_slight_hail_1,
        iconResNight = R.drawable.thunderstrom_with_slight_hail
    )

    object HeavyHailThunderstorm : WeatherType(
        weatherDescription = "Thunderstorm with heavy hail",
        iconResDay = R.drawable.thunderstrom_with_heavy_hail_1,
        iconResNight = R.drawable.thunderstrom_with_heavy_hail
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Fog
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> LightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> LightSnowShowers
                86 -> ModerateSnowShowers
                else -> ClearSky 
            }
        }
    }
} 