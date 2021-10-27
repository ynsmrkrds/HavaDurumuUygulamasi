package com.example.weatherapp.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Location

// an adapter for near locations and near cities list view
class LocationAdapter(
    private val context: Fragment,
    private val locations: List<Location>,
): BaseAdapter() {
    override fun getCount(): Int {
        return locations.size
    }

    override fun getItem(position: Int): Any {
        return locations[position]
    }

    override fun getItemId(position: Int): Long {
        return locations.indexOf(getItem(position)).toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.location_list_element, parent, false)

        // sets required fields using locations object
        rowView.findViewById<TextView>(R.id.location_name).text = locations[position].title
        rowView.findViewById<TextView>(R.id.distance).text = String.format("%.2f", locations[position].distance.toFloat() / 1000) // meter to km

        return rowView
    }
}