package com.asoom.skypulse.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha70Color
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha8Color
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha70Color
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha8Color
import com.asoom.skypulse.R
import androidx.compose.foundation.lazy.grid.items
import com.asoom.skypulse.domain.model.CurrentWeather
import com.asoom.skypulse.domain.model.WeatherInfoItem

@Composable
fun CurrentWeatherInfoList(currentWeather: CurrentWeather, isDay: Boolean) {
    val infoItems = listOf(
        WeatherInfoItem(
            title = "Wind",
            image = ImageVector.vectorResource(id = R.drawable.fast_wind),
            value = "${currentWeather.windSpeed} KM/h"
        ),
        WeatherInfoItem(
            title = "Humidity",
            image = ImageVector.vectorResource(id = R.drawable.humidity),
            value = "${currentWeather.humidity}%"
        ),
        WeatherInfoItem(
            title = "Rain",
            image = ImageVector.vectorResource(id = R.drawable.rain),
            value = "${currentWeather.precipitationProbability}%"
        ),
        WeatherInfoItem(
            title = "UV Index",
            image = ImageVector.vectorResource(id = R.drawable.uv),
            value = "${currentWeather.uvIndex.toInt()}"
        ),
        WeatherInfoItem(
            title = "Pressure",
            image = ImageVector.vectorResource(id = R.drawable.pressure),
            value = "${currentWeather.surfacePressure.toInt()} hPa"
        ),
        WeatherInfoItem(
            title = "Feels Like",
            image = ImageVector.vectorResource(id = R.drawable.temperature),
            value = "${currentWeather.apparentTemperature.toInt()}°C"
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .height(260.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        userScrollEnabled = false
    ) {
        items(infoItems) { item ->
            CurrentWeatherInfo(
                weatherInfoTitle = item.title,
                weatherInfoImage = item.image,
                weatherInfoValue = item.value,
                isDay = if (isDay) 1 else 0,
                modifier = Modifier
                    .height(115.dp)
                    .border(
                        width = 1.dp,
                        color = if (isDay) DarkPurpleAlpha8Color else WhiteColorAlpha8Color,
                        shape = RoundedCornerShape(size = 24.dp)
                    )
                    .background(
                        color = if (isDay) WhiteColorAlpha70Color else DarkPurpleAlpha70Color,
                        shape = RoundedCornerShape(size = 24.dp)
                    )
                    .fillMaxWidth()
            )
        }
    }
}
