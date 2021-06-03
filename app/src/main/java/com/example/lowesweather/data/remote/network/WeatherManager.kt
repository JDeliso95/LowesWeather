package com.example.lowesweather.data.remote.network

import com.example.lowesweather.data.models.WeatherResponseDTO
import retrofit2.Response

class WeatherManager {
    private val service: WeatherService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(WeatherService::class.java)
    }

    suspend fun getWeather(q: String, appId: String = "65d00499677e59496ca2f318eb68c049", units: String = "imperial"): Response<WeatherResponseDTO> {
        return service.getWeather(q, appId, units)
    }
}