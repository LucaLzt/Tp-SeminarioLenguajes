package com.example.tp_seminariodelenguajes

import java.io.Serializable

data class WeatherForecast(
    val hour: String,
    val date: String,
    val temperature: String,
    val weatherCondition: String,
    val iconResId: Int
)
