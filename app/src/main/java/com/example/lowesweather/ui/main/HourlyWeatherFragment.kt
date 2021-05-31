package com.example.lowesweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.lowesweather.R
import com.example.lowesweather.data.models.HourlyWeather
import com.example.lowesweather.data.models.WeatherResponseDTO
import com.example.lowesweather.databinding.HourlyWeatherBinding
import com.example.lowesweather.ui.main.adapters.OnItemClickListener
import com.example.lowesweather.ui.main.adapters.WeatherAdapter

class HourlyWeatherFragment : Fragment(), OnItemClickListener {

    private lateinit var binding: HourlyWeatherBinding

    private val weatherObserver = Observer<WeatherResponseDTO> {
        binding.weatherRv.adapter = WeatherAdapter(it.list, this@HourlyWeatherFragment)
    }

    companion object {
        fun newInstance() = HourlyWeatherFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onItemClicked(weather: HourlyWeather) {
        viewModel.selectedWeather = weather
        NavHostFragment.findNavController(this).navigate(R.id.action_hourlyWeatherFragment_to_singleWeatherFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = HourlyWeatherBinding.inflate(inflater, container, false)

        binding.weatherRv.adapter = WeatherAdapter(listOf(), this@HourlyWeatherFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.currentWeather.observe(viewLifecycleOwner, weatherObserver)
    }

}