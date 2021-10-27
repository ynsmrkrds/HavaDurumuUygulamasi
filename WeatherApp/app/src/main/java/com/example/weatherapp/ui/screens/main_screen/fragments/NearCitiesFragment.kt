package com.example.weatherapp.ui.screens.main_screen.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Location
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.ui.adapters.LocationAdapter
import com.example.weatherapp.ui.screens.weather_screen.WeatherActivity

// a fragment that represents near cities list view
class NearCitiesFragment(private var nearCities: List<Location> = listOf()) : Fragment() {

    private lateinit var listView: ListView // a list view for near cities

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_near_cities, container, false) as View

        listView = view.findViewById(R.id.near_cities_list_view) // gets list view from the view

        // sets list view adapter using location adapter that I wrote
        listView.adapter = LocationAdapter(this, nearCities)

        // listens the list view clicks
        // if there is any click, it opens the activity that shows the details of the relevant location
        listView.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    // gets selected item
                    val selectedItem = parent.getItemAtPosition(position) as Location

                    // creates an intent and sets the parameters to send to the activity
                    var intent = Intent(view.context, WeatherActivity::class.java)
                    intent.putExtra("woeid", selectedItem.woeid)
                    intent.putExtra("cityName", selectedItem.title)

                    // opens the activity
                    startActivity(intent)
                }

        return view
    }

    // sets the nearCities that a class variable with the parameter
    fun setNearCities(locations: List<Location>) {
        this.nearCities = locations
    }
}