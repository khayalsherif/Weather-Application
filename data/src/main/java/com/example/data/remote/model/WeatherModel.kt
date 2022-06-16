package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

class WeatherModel(
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("daily")
    val daily: List<Daily>
) {
    data class Daily(
        @SerializedName("temp")
        val temp: Temp,
        @SerializedName("wind_speed")
        val wind_speed: Double
    ) {
        data class Temp(
            @SerializedName("day")
            val day: Double,
            @SerializedName("eve")
            val eve: Double,
            @SerializedName("max")
            val max: Double,
            @SerializedName("min")
            val min: Double,
            @SerializedName("morn")
            val morn: Double,
            @SerializedName("night")
            val night: Double
        )
    }
}