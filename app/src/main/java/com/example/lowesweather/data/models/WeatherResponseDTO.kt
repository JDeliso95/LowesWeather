package com.example.lowesweather.data.models

data class WeatherResponseDTO(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<HourlyWeather>,
    val message: Int
)