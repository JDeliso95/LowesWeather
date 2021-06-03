package com.example.lowesweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lowesweather.R
import com.example.lowesweather.databinding.SingleWeatherBinding
import com.example.lowesweather.utils.loadWeatherImage

class SingleWeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private var binding: SingleWeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SingleWeatherBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel.selectedWeather?.let { hourlyWeather ->
            binding?.let {
                it.currentTempTv.text = hourlyWeather.main?.temp?.toInt().toString()
                it.feelsLikeTv.text = String.format(
                    getString(R.string.feels_like_temp),
                    hourlyWeather.main?.feelsLike?.toInt().toString()
                )
                it.weatherMainTv.text = hourlyWeather.weather[0].main
                it.specificWeatherDescriptionTv.text = hourlyWeather.weather[0].description
                it.weatherImage.loadWeatherImage(hourlyWeather.weather[0].icon)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}