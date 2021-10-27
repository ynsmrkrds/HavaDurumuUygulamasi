package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

// holds weather information of a location provided by api
data class Weather(
    @SerializedName("weather_state_name")
    val stateName: String,
    @SerializedName("weather_state_abbr")
    val stateAbbr: String,
    @SerializedName("applicable_date")
    val date: String,
    @SerializedName("the_temp")
    val temperature: Float,
    @SerializedName("wind_speed")
    val windSpeed: Float,
    @SerializedName("humidity")
    val humidity: Float,
)
