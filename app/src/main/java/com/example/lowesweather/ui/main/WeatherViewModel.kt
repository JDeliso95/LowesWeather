package com.example.lowesweather.ui.main

import androidx.lifecycle.*
import com.example.lowesweather.data.models.HourlyWeather
import com.example.lowesweather.data.models.WeatherResponseDTO
import com.example.lowesweather.data.repos.WeatherRepo
import com.example.lowesweather.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherViewModel : ViewModel() {
    private var _weatherResponse : MutableLiveData<WeatherResponseDTO> = MutableLiveData()
    val weatherResponse : LiveData<WeatherResponseDTO> get() = _weatherResponse

    private var _event: SingleLiveEvent<WeatherViewModelEvent> = SingleLiveEvent()
    val event: LiveData<WeatherViewModelEvent> get() = _event

    var selectedWeather : HourlyWeather? = null
    var cityQuery = ""

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            if (cityQuery.isBlank()) {
                _event.postValue(WeatherViewModelEvent.EmptySearchQuery)
            } else {
                _event.postValue(WeatherViewModelEvent.Loading)
                try {
                    val weatherResponse = WeatherRepo.getWeather(cityQuery).body()
                    if (!weatherResponse?.list.isNullOrEmpty()) {
                        _weatherResponse.postValue(weatherResponse)
                        _event.postValue(WeatherViewModelEvent.SearchSuccess)
                    } else {
                        _event.postValue(WeatherViewModelEvent.SearchError)
                    }
                } catch (e: Exception) {
                    _event.postValue(WeatherViewModelEvent.SearchError)
                }
            }
        }
    }

    sealed class WeatherViewModelEvent {
        object EmptySearchQuery : WeatherViewModelEvent()
        object Loading : WeatherViewModelEvent()
        object SearchSuccess : WeatherViewModelEvent()
        object SearchError : WeatherViewModelEvent()
    }
}