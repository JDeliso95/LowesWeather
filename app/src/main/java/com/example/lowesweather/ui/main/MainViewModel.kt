package com.example.lowesweather.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowesweather.data.models.HourlyWeather
import com.example.lowesweather.data.models.WeatherResponseDTO
import com.example.lowesweather.data.repos.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private var _currentWeather = MutableLiveData<WeatherResponseDTO>()
    val currentWeather: LiveData<WeatherResponseDTO> get() = _currentWeather

    lateinit var selectedWeather: HourlyWeather

    fun getWeather(selectedCity: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _currentWeather.postValue(WeatherRepo.getWeather(selectedCity).body())
            } catch (e: Exception){
                Log.e("TAG", e.toString())
            }
        }

    }

}