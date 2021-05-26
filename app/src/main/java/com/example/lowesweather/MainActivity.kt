package com.example.lowesweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.example.lowesweather.ui.main.CityLookupFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.nav_graph)
    }
}