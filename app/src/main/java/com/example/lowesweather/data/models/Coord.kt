package com.example.lowesweather.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    @field:Json(name="lat")val lat: Double?,
    @field:Json(name="lon")val lon: Double?
)