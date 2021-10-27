package com.example.weatherapp.ui.screens.main_screen


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Location
import com.example.weatherapp.ui.screens.main_screen.fragments.MainFragment
import com.example.weatherapp.ui.screens.main_screen.fragments.NearCitiesFragment
import com.example.weatherapp.ui.screens.main_screen.fragments.NearLocationsFragment
import com.example.weatherapp.ui.screens.weather_screen.WeatherViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel // view model of the screen

    // fragments that I used in the main activity outside the main fragment
    private lateinit var nearLocationsFragment: NearLocationsFragment
    private lateinit var nearCitiesFragment: NearCitiesFragment

    private lateinit var locations: List<Location> // holds the locations data coming from API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeCurrentFragment(MainFragment()) // sets main fragment as body

        // initializes the view model
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // initializes the fragments
        nearLocationsFragment = NearLocationsFragment()
        nearCitiesFragment = NearCitiesFragment()

        locations = listOf() // initializes the locations object

        // checks the location permission and performs the related operations
        checkLocationPermission()

        observeLocations() // observes the locations changes

        // listens the bottom bar clicks
        // if there is any click, it opens the relevant fragment
        findViewById<BottomNavigationView>(R.id.bottom_bar).setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nearLocations -> {
                    nearLocationsFragment.setNearLocations(locations) // sets the listView data

                    makeCurrentFragment(nearLocationsFragment) // sets near locations fragment as body
                    true
                }
                R.id.nearCities -> {
                    // sets the listView data
                    // by filters the locations according by location type
                    nearCitiesFragment.setNearCities(locations.filter {
                                e -> e.locationType == "City"
                            })

                    makeCurrentFragment(nearCitiesFragment) // sets near cities fragment as body
                    true
                }
                else -> false
            }
        }
    }

    // observes the locations data
    private fun observeLocations() {
        viewModel.locations.observe(this, Observer {
            locations = it
        })
    }

    // pulls locations data from api depending on device location
    private fun getLocations() {
        viewModel.deviceLocation.observe(this, Observer {
            viewModel.getLocations(it.toString())
        })
    }

    // places fragments in the wrapper object in the view
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.wrapper, fragment)
            commit()
        }
    }

    // checks the location permission
    private fun checkLocationPermission() {
        if (!isLocationPermissionGranted()) {
            // asks user for location information
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
            )
        } else {
            getLocations()
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (isLocationPermissionGranted()) {
                getLocations()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // returns whether the location permission has been received or not
    private fun isLocationPermissionGranted(): Boolean {
        return (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }
}
