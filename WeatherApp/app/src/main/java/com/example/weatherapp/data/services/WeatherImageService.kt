package com.example.weatherapp.data.services

import android.widget.ImageView
import com.bumptech.glide.Glide

// provides a service to pull weather icon from Meta Weather API
class WeatherImageService {
    companion object Factory {
        fun create(): WeatherImageService = WeatherImageService()

        private const val URL = "https://www.metaweather.com/static/img/weather/png/"

        // service that pulls weather icon
        fun getIcon(iconAbbr: String, imageView: ImageView) {
            Glide.with(imageView).load("$URL$iconAbbr.png").into(imageView)
        }
    }
}
