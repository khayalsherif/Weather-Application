package com.example.domain.use_case.get_weather

import com.example.domain.model.Weather
import com.example.domain.repository.WeatherRepository
import com.example.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
    ) {
    operator fun invoke(): Flow<NetworkResult<Weather>> = flow {
        try {
            emit(NetworkResult.Loading())
            val weather = weatherRepository.getWeather()
            emit(NetworkResult.Success(weather))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Has not Internet"))
        }
    }
}