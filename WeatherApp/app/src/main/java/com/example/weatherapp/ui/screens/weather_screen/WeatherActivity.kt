package com.example.weatherapp.ui.screens.weather_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.ui.adapters.WeatherListAdapter

class WeatherActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel // view model of the screen
    private lateinit var listView: ListView // a list view showing weather information

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        // initializes the view model
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java).apply {
            // pulls weathers data from api
            getWeathers(intent.getIntExtra("woeid", 0))
        }

        // initializes the list view
        listView = findViewById(R.id.weathers_list_view)

        // sets the city name field in the view
        findViewById<TextView>(R.id.city_name).text = intent.getStringExtra("cityName")

        observeWeathers() // observes the weather changes
    }

    // observes the weathers data
    private fun observeWeathers() {
        viewModel.weathers.observe(this, Observer {
            listView.adapter = WeatherListAdapter(this, it)
        })
    }
}