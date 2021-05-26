package com.example.lowesweather.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowesweather.data.models.HourlyWeather
import com.example.lowesweather.data.models.WeatherResponseDTO
import com.example.lowesweather.data.repos.WeatherRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    var _currentWeather = MutableLiveData<WeatherResponseDTO>()
    val currentWeather get() = _currentWeather

    lateinit var selectedWeather: HourlyWeather

    fun getWeather(selectedCity: String) {

        viewModelScope.launch {
            try {
                val getWeather = async { WeatherRepo.getWeather(selectedCity) }

                currentWeather.value = getWeather.await().body()
            } catch (e: Exception){
                Log.e("TAG", e.toString())
            }
        }

    }

}