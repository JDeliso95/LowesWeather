package com.example.lowesweather.data.models

import com.squareup.moshi.Json

data class Main(
    @Json(name = "feels_like")val feelsLike: Double,
    @Json(name = "grnd_level")val grndLevel: Int,
    val humidity: Int,
    val pressure: Int,
    @Json(name = "sea_level")val seaLevel: Int,
    val temp: Double,
    @Json(name = "temp_kf")val tempKf: Double,
    @Json(name = "temp_max")val tempMax: Int,
    @Json(name = "temp_min")val tempMin: Double
)