package com.asoom.skypulse.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class WeatherInfoItem(
    val title: String,
    val image: ImageVector,
    val value: String
)