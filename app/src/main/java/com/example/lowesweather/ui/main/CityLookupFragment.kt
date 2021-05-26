package com.example.lowesweather.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.lowesweather.R
import com.example.lowesweather.databinding.CityLookupFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CityLookupFragment : Fragment() {

    private lateinit var binding: CityLookupFragmentBinding

    companion object {
        fun newInstance() = CityLookupFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = CityLookupFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.searchButton.setOnClickListener(){

            viewModel.getWeather(binding.cityText.text.toString())

            NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_cityLookupFragment_to_hourlyWeatherFragment)

        }
    }

}