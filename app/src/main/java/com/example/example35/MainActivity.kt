package com.example.example35

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the view model
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        setContent {
            WeatherApp(weatherViewModel)
        }
    }

    private fun loadWeatherData(location: String) {
        // Pass the location to the view model
        weatherViewModel.setLocation(location)
    }

    @Composable
    fun WeatherApp(weatherViewModel: WeatherViewModel) {
        var location by remember { mutableStateOf("") }
        val weatherData = weatherViewModel.weatherData.collectAsState().value

        // Update UI elements based on the new weatherData value
        val temperature = weatherData?.let { "${Math.round(it.getTemperature().getTemp() - 273.15)} C" } ?: ""
        val humidity = weatherData?.getCurrentCondition()?.getHumidity()?.let { "$it%" } ?: ""
        val pressure = weatherData?.getCurrentCondition()?.getPressure()?.let { "$it hPa" } ?: ""

        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Enter location:")
                TextField(
                    value = location,
                    onValueChange = { newLocation -> location = newLocation },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Button(onClick = { weatherViewModel.setLocation(location) }) {
                    Text(text = "Submit")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Temperature: $temperature")
            Text(text = "Humidity: $humidity")
            Text(text = "Pressure: $pressure")
        }
    }


}
