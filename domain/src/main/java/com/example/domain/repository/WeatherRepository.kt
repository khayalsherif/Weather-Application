package com.example.domain.repository

import com.example.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(): Weather
}