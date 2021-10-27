package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

// holds location information provided by api
data class Location(
    @SerializedName("distance")
    val distance:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("location_type")
    val locationType:String,
    @SerializedName("woeid")
    val woeid:Int,
    @SerializedName("latt_long")
    val lattLong:String,
)
