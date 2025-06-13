package com.asoom.skypulse.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.asoom.skypulse.R
import com.asoom.skypulse.domain.model.CurrentWeather
import com.asoom.skypulse.domain.model.DailyWeather
import com.asoom.skypulse.domain.util.WeatherType
import com.asoom.skypulse.presentation.theme.*

@Composable
fun CurrentWeatherInfo(
    currentWeather: CurrentWeather,
    dailyWeather: DailyWeather?,
    isDay: Boolean,
    isRow: Boolean
) {
    val weatherType = WeatherType.fromWMO(currentWeather.weatherCode)
    val icon = if (currentWeather.isDay == 1) weatherType.iconResDay else weatherType.iconResNight
    val currentTemperature = "${currentWeather.temperature.toInt()}°C"
    val weatherStatus = weatherType.weatherDescription
    val maxTemperatureOfTheDay = "${dailyWeather?.maxTemp?.getOrNull(0)?.toInt() ?: 0}°C"
    val minimumTemperatureOfTheDay = "${dailyWeather?.minTemp?.getOrNull(0)?.toInt() ?: 0}°C"

    AnimatedContent(
        targetState = isRow,
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) + slideInVertically(
                animationSpec = tween(400),
                initialOffsetY = { it / 2 }
            ) + scaleIn(
                animationSpec = tween(400),
                initialScale = 0.95f
            ) togetherWith
                    fadeOut(animationSpec = tween(100)) + slideOutVertically(
                animationSpec = tween(150),
                targetOffsetY = { -it  }
            ) + scaleOut(
                animationSpec = tween(150),
                targetScale = 1.05f
            ) using SizeTransform(clip = false)
        },
        label = "Switch Layout"
    ) { rowMode ->
        if (!rowMode) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherInformationContent(
                    icon = icon,
                    currentTemperature = currentTemperature,
                    weatherStatus = weatherStatus,
                    maxTemperatureOfTheDay = maxTemperatureOfTheDay,
                    minimumTemperatureOfTheDay = minimumTemperatureOfTheDay,
                    isDay = isDay,
                    imageSize = 200.dp
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                WeatherInformationContent(
                    icon = icon,
                    currentTemperature = currentTemperature,
                    weatherStatus = weatherStatus,
                    maxTemperatureOfTheDay = maxTemperatureOfTheDay,
                    minimumTemperatureOfTheDay = minimumTemperatureOfTheDay,
                    isDay = isDay,
                    imageSize = 124.dp
                )
            }
        }
    }
}

@Composable
fun WeatherInformationContent(
    @DrawableRes icon: Int,
    currentTemperature: String,
    weatherStatus: String,
    maxTemperatureOfTheDay: String,
    minimumTemperatureOfTheDay: String,
    isDay: Boolean,
    imageSize: Dp
) {
    Box(
        modifier = Modifier.size(imageSize)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(130.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .clip(CircleShape)
                .background(
                    HomeScreenUtils
                        .extractDominantColor(
                            LocalContext.current,
                            icon
                        ).copy(alpha = 0.08f),
                    shape = CircleShape
                )
        )

        AsyncImage(
            model = icon,
            contentDescription = "Weather Icon",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentTemperature,
            style = TextStyle(
                fontSize = 64.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                fontWeight = FontWeight(600),
                color = if (isDay) DarkPurpleAlpha60Color else WhiteColor,
            )
        )

        Text(
            text = weatherStatus,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                fontWeight = FontWeight(500),
                color = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha60Color,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier
                .background(
                    color = if (isDay) DarkPurpleAlpha8Color else WhiteColorAlpha8Color,
                    shape = RoundedCornerShape(size = 100.dp)
                )
                .padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_up),
                    contentDescription = "Arrow Up",
                    tint = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha87Color
                )

                Text(
                    text = maxTemperatureOfTheDay,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                        fontWeight = FontWeight(500),
                        color = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha87Color,
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.divder),
                contentDescription = "divider",
                modifier = Modifier.padding(horizontal = 8.dp),
                tint = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha87Color
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_down),
                    contentDescription = "Arrow down",
                    tint = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha87Color
                )

                Text(
                    text = minimumTemperatureOfTheDay,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                        fontWeight = FontWeight(500),
                        color = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha87Color,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}
