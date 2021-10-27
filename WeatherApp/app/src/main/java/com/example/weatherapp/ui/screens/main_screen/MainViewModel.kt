package com.example.weatherapp.ui.screens.main_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.models.Location
import com.example.weatherapp.data.services.WeatherApiService
import com.example.weatherapp.utils.LocationLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

// a view model class for main screen
class MainViewModel(application: Application) : AndroidViewModel(application) {
    // holds the location of device
    val deviceLocation: LocationLiveData = LocationLiveData(application)

    val locations = MutableLiveData<List<Location>>() // a live data object that holds locations data

    fun getLocations(latitudeAndLongitude: String) {
        CompositeDisposable().add(
                WeatherApiService.getLocation(latitudeAndLongitude)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Location>>() {
                            override fun onSuccess(t: List<Location>) {
                                locations.value = t
                            }

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                            }
                        })
        )
    }
}