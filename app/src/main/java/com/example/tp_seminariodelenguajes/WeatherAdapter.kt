package com.example.tp_seminariodelenguajes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(
    private val weatherList: List<WeatherForecast>):
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val hourTextView: TextView = view.findViewById(R.id.tvHour)
        val dateTextView: TextView = view.findViewById(R.id.tvDate)
        val temperatureTextView: TextView = view.findViewById(R.id.tvTemperature)
        val weatherConditionTextView: TextView = view.findViewById(R.id.tvWeatherCondition)
        val weatherIconImageView: ImageView = view.findViewById(R.id.ivWeatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.hourTextView.text = weather.hour
        holder.dateTextView.text = weather.date
        holder.temperatureTextView.text = weather.temperature
        holder.weatherConditionTextView.text = weather.weatherCondition
        holder.weatherIconImageView.setImageResource(weather.iconResId)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}