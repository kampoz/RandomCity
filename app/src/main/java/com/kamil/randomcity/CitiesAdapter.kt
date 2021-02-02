package com.kamil.randomcity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CitiesAdapter(private val citieslist: MutableList<CityItem>): RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = citieslist.get(position)
        holder.cityName.setText(currentItem.cityName)
        holder.cityName.setTextColor(Color.parseColor(currentItem.color))
        holder.date.setText(currentItem.date)
    }

    override fun getItemCount() = citieslist.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.textview_city_name)
        val date: TextView = itemView.findViewById(R.id.textview_date)
    }

}
