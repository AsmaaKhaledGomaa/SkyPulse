package com.asoom.skypulse.data.repository

import android.content.Context
import android.location.Geocoder
import com.asoom.skypulse.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.Locale

class LocationRepositoryImpl(
    private val context: Context
) : LocationRepository {

    override suspend fun getCityName(latitude: Double, longitude: Double): String? = withContext(Dispatchers.IO) {
        val geocoder = Geocoder(context, Locale.getDefault())
        return@withContext try {
            @Suppress("DEPRECATION")
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            addresses?.firstOrNull()?.locality
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            null
        }
    }
} 