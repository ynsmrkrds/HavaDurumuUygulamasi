package com.example.weatherapp.ui.screens.main_screen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Location
import com.example.weatherapp.ui.adapters.LocationAdapter

// a fragment that represents near locations list view
class NearLocationsFragment(private var nearLocations: List<Location> = listOf()) : Fragment() {

    private lateinit var listView: ListView // a list view for near locations

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_near_locations, container, false) as View

        listView = view.findViewById(R.id.near_locations_list_view) // gets list view from the view

        // sets list view adapter using location adapter that I wrote
        val myListAdapter = LocationAdapter(this, nearLocations)
        listView.adapter = myListAdapter

        return view
    }

    // sets the nearLocations that a class variable with the parameter
    fun setNearLocations(locations: List<Location>) {
        this.nearLocations = locations
    }
}