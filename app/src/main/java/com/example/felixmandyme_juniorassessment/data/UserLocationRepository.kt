package com.example.felixmandyme_juniorassessment.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import java.util.Locale

interface UserLocationRepository{
    suspend fun getCity(): String
}

class UserLocationRepositoryImpl(
    private val fusedLocationClient: FusedLocationProviderClient,
    @ApplicationContext private val context: Context
): UserLocationRepository{
    override suspend fun getCity(): String = suspendCancellableCoroutine { cont ->
        val hasFine = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasCoarse = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasFine && !hasCoarse) {
            cont.resume("Location permission not granted") {}
            return@suspendCancellableCoroutine
        }

        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    try {
                        val geocoder = Geocoder(context, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        val city = addresses?.firstOrNull()?.locality ?: "City Not Found"
                        cont.resume(city) {}
                    } catch (e: IOException) {
                        cont.resume("Error: ${e.message}") {}
                    }
                } else {
                    cont.resume("Location not found") {}
                }
            }.addOnFailureListener {
                cont.resume("Failed to get location") {}
            }
        } catch (e: SecurityException) {
            cont.resume("SecurityException: ${e.message}") {}
        }
    }
}