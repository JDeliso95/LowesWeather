package com.example.lowesweather.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.lowesweather.R
import com.example.lowesweather.databinding.CityLookupFragmentBinding
import com.example.lowesweather.utils.navigate

class CityLookupFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private var binding: CityLookupFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = CityLookupFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            it.cityText.isEnabled = true
            it.searchButton.isEnabled = true

            it.searchButton.setOnClickListener {
                weatherViewModel.getWeather()
            }

            it.cityText.addTextChangedListener { text ->
                weatherViewModel.cityQuery = text.toString()
            }

            weatherViewModel.event.observe(viewLifecycleOwner, Observer { event ->
                when (event) {
                    is WeatherViewModel.WeatherViewModelEvent.EmptySearchQuery -> {
                        it.cityText.error = getString(R.string.city_lookup_empty_query)
                        it.cityEt.isEnabled = true
                        it.progressBar.visibility = View.GONE
                    }
                    is WeatherViewModel.WeatherViewModelEvent.Loading -> {
                        it.cityText.error = null
                        it.cityEt.isEnabled = false
                        it.searchButton.isEnabled = false
                        it.progressBar.visibility = View.VISIBLE
                    }
                    is WeatherViewModel.WeatherViewModelEvent.SearchSuccess -> {
                        it.progressBar.visibility = View.GONE
                        navigate(R.id.action_cityLookupFragment_to_hourlyWeatherFragment)
                    }
                    is WeatherViewModel.WeatherViewModelEvent.SearchError -> {
                        it.cityText.error = getString(R.string.weather_search_error)
                        it.cityEt.isEnabled = true
                        it.searchButton.isEnabled = true
                        it.progressBar.visibility = View.GONE
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}