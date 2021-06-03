package com.example.lowesweather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.example.lowesweather.databinding.MainActivityBinding
import com.example.lowesweather.ui.main.WeatherViewModel
import java.util.*

class MainActivity : FragmentActivity() {

    private lateinit var binding: MainActivityBinding
    private val weatherViewModel : WeatherViewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val backArrowDrawable = AppCompatResources.getDrawable(this, R.drawable.ic_back_arrow_white)

        with(navController) {
            addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.cityLookupFragment -> {
                        binding.myToolbar.apply {
                            title = getString(R.string.app_name)
                            navigationIcon = null
                        }
                    }
                    R.id.hourlyWeatherFragment -> {
                        binding.myToolbar.apply {
                            title = getString(R.string.app_name)
                           navigationIcon = backArrowDrawable
                            setNavigationOnClickListener {
                                navigateUp()
                            }
                        }
                    }
                    R.id.singleWeatherFragment -> {
                        binding.myToolbar.apply {
                            title = weatherViewModel.cityQuery.capitalize()
                            navigationIcon = backArrowDrawable
                            setNavigationOnClickListener {
                                navigateUp()
                            }
                        }
                    }
                }
            }
            setGraph(R.navigation.nav_graph)
        }
    }
}