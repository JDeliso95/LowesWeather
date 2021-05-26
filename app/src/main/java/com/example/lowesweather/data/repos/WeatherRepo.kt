package com.example.lowesweather.data.repos

import com.example.lowesweather.data.models.WeatherResponseDTO
import com.example.lowesweather.data.remote.network.WeatherManager
import retrofit2.Response

object WeatherRepo {
    suspend fun getWeather(q: String) : Response<WeatherResponseDTO> {
        return WeatherManager().getWeather(q)
    }
}