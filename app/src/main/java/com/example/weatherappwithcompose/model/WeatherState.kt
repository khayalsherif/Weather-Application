package com.example.weatherappwithcompose.model

import com.example.domain.model.Weather

data class WeatherState(
    val hasInternet: Boolean = true,
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String = ""
)