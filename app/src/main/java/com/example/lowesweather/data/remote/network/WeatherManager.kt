package com.example.lowesweather.data.remote.network

import com.example.lowesweather.data.models.WeatherResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherManager {
    private val service: WeatherService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(WeatherService::class.java)
    }

    suspend fun getWeather(q: String, appId: String = "65d00499677e59496ca2f318eb68c049", units: String = "imperial"): Response<WeatherResponseDTO> {
        return service.getWeather(q, appId, units)
    }

    interface WeatherService {
        @GET("/data/2.5/forecast")
        suspend fun getWeather(
            @Query("q" ) q: String,
            @Query("appId" ) appId: String,
            @Query("units") units: String
        ): Response<WeatherResponseDTO>
    }

}