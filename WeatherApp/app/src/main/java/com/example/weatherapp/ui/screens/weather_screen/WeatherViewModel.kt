package com.example.weatherapp.ui.screens.weather_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.services.WeatherApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDate

// a view model class for weather screen
class WeatherViewModel: ViewModel() {
    // Since the 8-day weather data did not come all at once,
    // something like this was needed to show the data sequentially on the screen
    private val temp = mutableListOf<Weather>()

    val weathers = MutableLiveData<List<Weather>>() // a live data object that holds weathers data

    // pulls weather information for today and next 1 week
    fun getWeathers(woeid: Int) {
        for (i in 0..7) {
            val date = LocalDate.now().plusDays(i.toLong())

            getWeather(woeid, date.year, date.month.value, date.dayOfMonth)
        }
    }

    // pulls weather information from API using related parameters
    private fun getWeather(woeid: Int, year:Int, month: Int, day:Int) {
        CompositeDisposable().add(
                WeatherApiService.getWeather(woeid,year,month,day)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Weather>>() {
                            override fun onSuccess(t: List<Weather>) {
                                // gets actual weather from api and sorts by date
                                temp.add(t[0])
                                temp.sortBy { it.date }

                                // assigns it to weathers object
                                weathers.value = temp
                            }

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                            }
                        })
        )
    }
}