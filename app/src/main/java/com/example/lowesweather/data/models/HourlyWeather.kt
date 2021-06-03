package com.example.lowesweather.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyWeather(
    @field:Json(name = "clouds")val clouds: Clouds?,
    @field:Json(name = "dt")val dt: Int?,
    @field:Json(name = "dt_txt") val dtTxt: String?,
    @field:Json(name = "main")val main: Main?,
    @field:Json(name = "pop")val pop: Double?,
    @field:Json(name = "rain")val rain: Rain?,
    @field:Json(name = "sys")val sys: Sys?,
    @field:Json(name = "visibility")val visibility: Int?,
    @field:Json(name = "weather")val weather: List<Weather>,
    @field:Json(name = "wind")val wind: Wind?
)
