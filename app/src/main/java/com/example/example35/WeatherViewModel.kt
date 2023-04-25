package com.example.example35

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val _weatherData = MutableStateFlow<WeatherData?>(null)
    val weatherData: StateFlow<WeatherData?> = _weatherData.asStateFlow()

    private val weatherRepository = WeatherRepository.getInstance(application)

    fun setLocation(location: String) {
        weatherRepository.setLocation(location)
    }

    init {
        weatherRepository.getData().observeForever { newData ->
            _weatherData.value = newData
        }
    }
}
