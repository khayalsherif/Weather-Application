package com.example.data.remote.api

import com.example.data.extensions.EndPoints
import com.example.data.remote.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(EndPoints.WEATHER)
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") key: String
    ): WeatherModel
}