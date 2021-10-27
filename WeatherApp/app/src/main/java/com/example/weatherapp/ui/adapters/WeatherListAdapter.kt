package com.example.weatherapp.ui.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.services.WeatherImageService

// an adapter for weathers list view
class WeatherListAdapter(
    private val context: Activity,
    private val weathers: List<Weather>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return weathers.size
    }

    override fun getItem(position: Int): Any {
        return weathers[position]
    }

    override fun getItemId(position: Int): Long {
        return weathers.indexOf(getItem(position)).toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.weather_list_element, parent, false)

        // sets the icon field using Weather Image Service
        WeatherImageService.getIcon(weathers[position].stateAbbr, rowView.findViewById<ImageView>(R.id.state_icon))

        // sets other required fields using weathers object
        rowView.findViewById<TextView>(R.id.state_name).text = weathers[position].stateName
        rowView.findViewById<TextView>(R.id.temperature).text = weathers[position].temperature.toInt().toString()
        rowView.findViewById<TextView>(R.id.humidity).text = weathers[position].humidity.toInt().toString()
        rowView.findViewById<TextView>(R.id.wind_speed).text = String.format("%.1f", weathers[position].windSpeed)
        rowView.findViewById<TextView>(R.id.date).text = weathers[position].date

        return rowView
    }
}