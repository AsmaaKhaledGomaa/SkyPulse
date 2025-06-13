package com.asoom.skypulse.presentation.component

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette

object HomeScreenUtils {
    fun extractDominantColor(context: Context, @DrawableRes drawableRes: Int): Color {
        val drawable = ContextCompat.getDrawable(context, drawableRes)
        val bitmap = drawable?.toBitmap()

        return bitmap?.let {
            val palette = Palette.from(it).generate()
            Color(palette.getDominantColor(Color.Gray.toArgb()))
        } ?: Color.Gray
    }
}