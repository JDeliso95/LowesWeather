package com.example.lowesweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesweather.R
import com.example.lowesweather.data.models.HourlyWeather
import com.example.lowesweather.databinding.HourlyWeatherBinding
import com.example.lowesweather.ui.main.adapters.WeatherAdapter
import com.example.lowesweather.utils.navigate

class HourlyWeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private var binding: HourlyWeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HourlyWeatherBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.weatherResponse.observe(viewLifecycleOwner, Observer { weatherResponse ->
            binding?.let {
                it.weatherRv.apply {
                    adapter = WeatherAdapter(
                        weatherResponse.list,
                        this@HourlyWeatherFragment::onWeatherClicked
                    )
                    addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
                }
            }
        })
    }

    private fun onWeatherClicked(weather: HourlyWeather) {
        weatherViewModel.selectedWeather = weather
        navigate(R.id.action_hourlyWeatherFragment_to_singleWeatherFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}