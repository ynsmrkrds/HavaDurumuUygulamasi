package com.example.weatherapp.data.services

import com.example.weatherapp.data.models.Location
import com.example.weatherapp.data.models.Weather
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// provides services to pull data from Meta Weather API
class WeatherApiService {
    companion object Factory {
        fun create(): WeatherApiService = WeatherApiService()

        // base URL of the API
        private const val BASE_URL = "https://www.metaweather.com/api/location/"

        // retrofit object
        private val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(WeatherApiInterface::class.java)

        // service that pulls location information
        fun getLocation(latitudeAndLongitude: String): Single<List<Location>> {
            return api.getLocation(latitudeAndLongitude)
        }

        // service that pulls weather information of an location
        fun getWeather(woeid: Int, year: Int, month: Int, day: Int,): Single<List<Weather>> {
            return api.getWeather(woeid, year, month, day)
        }
    }
}

// an interface for Retrofit
interface WeatherApiInterface {
    @GET("search")
    fun getLocation(@Query("lattlong") lattLong: String): Single<List<Location>>

    @GET("{woeid}/{year}/{month}/{day}")
    fun getWeather(
        @Path("woeid") woeid: Int,
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Path("day") day: Int,
    ): Single<List<Weather>>
}