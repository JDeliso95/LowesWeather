package com.example.lowesweather.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesweather.data.models.HourlyWeather
import com.example.lowesweather.databinding.WeatherItemBinding

class WeatherAdapter(
    private val weather: List<HourlyWeather>,
    val listener: OnItemClickListener): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = weather.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int): Unit = with(holder) {
        val weatherItem = weather[position]
        holder.bind(weatherItem, listener)
    }

    inner class WeatherViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(weatherItem: HourlyWeather, listener: OnItemClickListener) = with(binding) {

            itemView.setOnClickListener{
                listener.onItemClicked(weatherItem)
            }

            binding.weatherDescription.text = weatherItem.weather[0].description
            binding.temp.text = weatherItem.main.temp.toInt().toString()
        }
    }


}

interface OnItemClickListener{
    fun onItemClicked(hour: HourlyWeather)
}