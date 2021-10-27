package com.example.weatherapp.utils

/*
* I wrote this class using
* "https://proandroiddev.com/android-tutorial-on-location-update-with-livedata-774f8fcc9f15"
* */

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import com.google.android.gms.location.*

// a class that emits device location information as Live Data
class LocationLiveData(context: Context) : LiveData<String>() {
    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: android.location.Location? ->
                location?.also {
                    setLocationData(it)
                }
            }

        startLocationUpdates()
    }

    companion object {
        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval = 1000
            smallestDisplacement = 100f
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private fun setLocationData(location: android.location.Location) {
        value = "${location.latitude}, ${location.longitude}"
    }
}