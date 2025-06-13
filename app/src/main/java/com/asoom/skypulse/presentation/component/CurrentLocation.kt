package com.asoom.skypulse.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asoom.skypulse.R
import com.asoom.skypulse.presentation.theme.DarkPurpleBottomColor
import com.asoom.skypulse.presentation.theme.LightBlackColor
import com.asoom.skypulse.presentation.theme.Urbanist
import com.asoom.skypulse.presentation.theme.WhiteColor

@Composable
fun CurrentLocation(cityName: String, isDay: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Icon(modifier = Modifier.size(28.dp),
            painter = painterResource(R.drawable.location_ic),
            contentDescription = "Location Icon",
            tint = if (isDay) LightBlackColor else WhiteColor
        )
        Text(
            modifier = Modifier.padding(start = 4.dp, top = 2.dp, bottom = 2.dp),
            text = cityName,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.25.sp,
            color = if (isDay) LightBlackColor else WhiteColor
        )
    }
}