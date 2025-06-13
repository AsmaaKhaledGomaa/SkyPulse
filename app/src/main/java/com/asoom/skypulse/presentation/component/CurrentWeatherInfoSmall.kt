package com.asoom.skypulse.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asoom.skypulse.R
import com.asoom.skypulse.domain.model.CurrentWeather
import com.asoom.skypulse.domain.model.DailyWeather
import com.asoom.skypulse.presentation.theme.*
import com.asoom.skypulse.domain.util.WeatherType

@Composable
fun CurrentWeather(currentWeather: CurrentWeather, dailyWeather: DailyWeather?, isDay: Boolean) {
    val weatherType = WeatherType.fromWMO(currentWeather.weatherCode)
    val weatherImageRes = if (currentWeather.isDay == 1) weatherType.iconResDay else weatherType.iconResNight
    val weatherDescription = weatherType.weatherDescription

    val highTemp = dailyWeather?.maxTemp?.getOrNull(0)?.toInt() ?: 0
    val lowTemp = dailyWeather?.minTemp?.getOrNull(0)?.toInt() ?: 0

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = 15.5.dp)
                .padding(end = 44.dp)
                .weight(1f),
            painter = painterResource(weatherImageRes),
            contentDescription = weatherDescription
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp).padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally),
                    text = "${currentWeather.temperature.toInt()}°C",
                    color = if (isDay) DarkPurpleBottomColor else WhiteColor,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 64.sp,
                    lineHeight = 100.sp,
                    letterSpacing = 0.25.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = weatherDescription,
                    color = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha60Color,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.25.sp
                )
            }
            TemperatureCard(high = "${highTemp}°C", low = "${lowTemp}°C", isDay)
        }
    }
}

@Composable
private fun TemperatureCard(high: String, low: String, isDay: Boolean) {
    Card(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(100.dp),
        colors = CardDefaults.cardColors(containerColor = if (isDay) DarkPurpleAlpha8Color else WhiteColorAlpha8Color)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(vertical = 8.dp, horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TemperatureItem(
                modifier = Modifier.weight(1f),
                icon = R.drawable.arrow_up,
                value = high,
                isDay = isDay
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(1.dp)
                    .height(14.dp)
                    .background(if (isDay) DarkPurpleAlpha24Color else WhiteColorAlpha60Color)
            )

            TemperatureItem(
                modifier = Modifier.weight(1f),
                icon = R.drawable.arrow_down,
                value = low,
                isDay = isDay
            )
        }
    }
}

@Composable
private fun TemperatureItem(modifier: Modifier, icon: Int, value: String, isDay: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        Icon(
            modifier = Modifier.size(24.dp)
                .padding(vertical = 3.5.dp)
                .padding(end = 4.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha87Color
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = value,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.25.sp,
            color = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha87Color
        )
    }
}

@Composable
fun CurrentWeatherInfo(
    modifier: Modifier = Modifier,
    weatherInfoTitle: String,
    weatherInfoImage: ImageVector,
    weatherInfoValue: String,
    isDay: Int,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 38.dp, vertical = 8.dp)
                    .size(32.dp),
                imageVector = weatherInfoImage,
                contentDescription = "Weather Icon"
            )

            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = weatherInfoValue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                    fontWeight = FontWeight.Medium,
                    color = if (isDay == 1) DarkPurpleAlpha87Color else WhiteColorAlpha87Color,
                    textAlign = TextAlign.Center,
                )
            )

            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                text = weatherInfoTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                    fontWeight = FontWeight.Normal,
                    color = if (isDay == 1) DarkPurpleAlpha60Color else WhiteColorAlpha60Color,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}
