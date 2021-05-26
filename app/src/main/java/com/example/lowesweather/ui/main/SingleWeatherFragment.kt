package com.example.lowesweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lowesweather.R
import com.example.lowesweather.databinding.CityLookupFragmentBinding
import com.example.lowesweather.databinding.SingleWeatherBinding

@Suppress("DEPRECATION")
class SingleWeatherFragment : Fragment() {

    private lateinit var binding: SingleWeatherBinding

    companion object {
        fun newInstance() = SingleWeatherFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = SingleWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val selectedWeather = viewModel.selectedWeather

        binding.currentTempTv.text = selectedWeather.main.temp.toInt().toString()
        binding.feelsLikeTv.text = selectedWeather.main.feelsLike.toInt().toString()
        binding.weatherDescriptionTv.text = selectedWeather.weather[0].main
        binding.weatherDescriptionTv.text = selectedWeather.weather[0].description

    }

}