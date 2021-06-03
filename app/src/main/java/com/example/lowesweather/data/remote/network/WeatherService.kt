package com.example.lowesweather.data.remote.network

import com.example.lowesweather.data.models.WeatherResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/forecast")
    suspend fun getWeather(
        @Query("q" ) q: String,
        @Query("appId" ) appId: String,
        @Query("units") units: String
    ): Response<WeatherResponseDTO>
}