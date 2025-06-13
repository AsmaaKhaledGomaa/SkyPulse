package com.asoom.skypulse.presentation.component

import com.asoom.skypulse.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha24Color
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha60Color
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha70Color
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha87Color
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha8Color
import com.asoom.skypulse.presentation.theme.DarkPurpleTopColor
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha60Color
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha70Color
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha87Color
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha8Color

@Composable
fun DailyForecastList(
    modifier: Modifier = Modifier,
    days: List<String>,
    maxTemperaturesOfDay: List<String>,
    minimumTemperaturesOfDay: List<String>,
    @DrawableRes weatherImages: List<Int>,
    isDay: Boolean
) {

    Column(
        modifier = Modifier.padding(horizontal = 12.dp)
            .border(
                width = 1.dp,
                color = if (isDay) DarkPurpleAlpha8Color else WhiteColorAlpha8Color,
                shape = RoundedCornerShape(size = 20.dp)
            )
            .clip(RoundedCornerShape(24.dp))
            .background(if (isDay) WhiteColorAlpha70Color else DarkPurpleAlpha70Color),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        days.forEachIndexed { index, day ->
            DailyForecastItem(
                day = day,
                highTemp = maxTemperaturesOfDay[index],
                lowTemp = minimumTemperaturesOfDay[index],
                weatherIcon = weatherImages[index],
                isLastRow = index == days.lastIndex,
                isDay = isDay
            )
        }
    }
}

@Composable
private fun DailyForecastItem(
    day: String,
    highTemp: String,
    lowTemp: String,
    @DrawableRes weatherIcon: Int,
    isLastRow: Boolean,
    isDay: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(0.3f),
            text = day,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                fontWeight = FontWeight(400),
                color = if (isDay) DarkPurpleAlpha60Color else WhiteColorAlpha60Color,
            )
        )

        Box(
            modifier = Modifier.size(50.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(145.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .clip(CircleShape)
//                    .background(CircleShape)
            )

            Image(
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp),
                painter = painterResource(weatherIcon),
                contentDescription = "Sun icon",
                contentScale = ContentScale.FillWidth
            )
        }

        Box(
            modifier = Modifier.weight(0.4f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(.80f)
                    .clip(shape = RoundedCornerShape(size = 100.dp))
                    .padding(top = 8.dp, end = 0.dp, bottom = 8.dp)
                    .align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TemperatureItem(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.arrow_up,
                        value = highTemp,
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
                        value = lowTemp,
                        isDay = isDay
                    )
                }
            }
        }
    }

    if (!isLastRow) HorizontalDivider(
        thickness = 1.dp,
        color = if (isDay) Color(0x14060414) else Color(0x14FFFFFF)
    )
}

@Composable
private fun TemperatureItem(modifier: Modifier, icon: Int, value: String, isDay: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        Icon(
            modifier = Modifier
                .padding(vertical = 3.5.dp)
                .padding(end = 4.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = if (isDay) DarkPurpleAlpha87Color else WhiteColorAlpha87Color
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = value,
            color = if (isDay) DarkPurpleAlpha87Color else WhiteColorAlpha87Color
        )
    }
}