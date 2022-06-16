package com.example.data.repository

import com.example.data.remote.api.WeatherApi
import com.example.data.mapper.toWeather
import com.example.domain.model.Weather
import com.example.domain.repository.WeatherRepository
import com.example.domain.utils.Constants.BASE_KEY
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val latitude: Double,
    private val longitude: Double
) : WeatherRepository {
    override suspend fun getWeather(): Weather {
        return weatherApi.getWeather(latitude, longitude, BASE_KEY).toWeather()
    }
}