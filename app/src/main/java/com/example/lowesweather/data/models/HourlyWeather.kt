package com.example.lowesweather.data.models

import com.squareup.moshi.Json

data class HourlyWeather(
    val clouds: Clouds,
    val dt: Int,
    @Json(name = "dt_txt") val dtTxt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
