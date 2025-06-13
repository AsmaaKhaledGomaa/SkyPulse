package com.asoom.skypulse.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asoom.skypulse.R
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha70Color
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha87Color
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha60Color
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha87Color

@Composable
fun TodayWeather(
    weatherImage: Painter,
    weatherTemperature: String,
    currentHour: String,
    isDay: Int,
    modifier: Modifier = Modifier
) {
    Box {
        Box(
            modifier
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = weatherTemperature,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            fontWeight = FontWeight(500),
                            color = if (isDay == 1) DarkPurpleAlpha87Color else WhiteColorAlpha87Color,
                            textAlign = TextAlign.Center,
                        )
                    )

                    Text(
                        text = currentHour,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            fontWeight = FontWeight(500),
                            color = if (isDay == 1) DarkPurpleAlpha70Color else WhiteColorAlpha60Color,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }

        Image(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-12).dp),
            painter = weatherImage,
            contentDescription = "$weatherImage image",
            contentScale = ContentScale.FillBounds
        )
    }
}