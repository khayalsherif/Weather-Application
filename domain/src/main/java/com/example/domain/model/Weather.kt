package com.example.domain.model

class Weather(
    val timezone: String,
    val daily: List<Daily>
) {
    data class Daily(
        val temp: Temp,
        val wind_speed: Double
    ) {
        data class Temp(
            val day: Double,
            val eve: Double,
            val max: Double,
            val min: Double,
            val morn: Double,
            val night: Double
        )

    }

}