package com.example.data.mapper

import com.example.data.remote.model.WeatherModel
import com.example.domain.model.Weather

fun WeatherModel.toWeather() =
    Weather(timezone = timezone, daily = daily.map { it.toWeatherDaily() })


fun WeatherModel.Daily.toWeatherDaily() =
    Weather.Daily(temp = temp.toWeatherTemp(), wind_speed = wind_speed)

fun WeatherModel.Daily.Temp.toWeatherTemp() =
    Weather.Daily.Temp(
        day = day,
        eve = eve,
        max = max,
        min = min,
        morn = morn,
        night = night
    )