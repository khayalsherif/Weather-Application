package com.example.weatherappwithcompose.view_model

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.data.local.DataStoreRepository
import com.example.domain.use_case.get_weather.GetWeatherUseCase
import com.example.domain.utils.NetworkResult
import com.example.weatherappwithcompose.base.BaseViewModel
import com.example.weatherappwithcompose.model.WeatherState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val getWeatherUseCase: GetWeatherUseCase,
    dataStoreRepository: DataStoreRepository
) : BaseViewModel() {

    private val hasInternetConnection = hasInternetConnection(application)

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    private var _userCurrentLng = MutableStateFlow(0.0)
    var userCurrentLng: StateFlow<Double> = _userCurrentLng

    private var _userCurrentLat = MutableStateFlow(0.0)
    var userCurrentLat: StateFlow<Double> = _userCurrentLat

    val pickUp = LatLng(userCurrentLat.value, userCurrentLng.value)

    init {
        getWeather()
    }

    private fun getWeather() {
        getWeatherUseCase().onEach { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _state.emit(WeatherState(weather = result.data))
                }
                is NetworkResult.Error -> {
                    if (!hasInternetConnection) {
                        _state.emit(WeatherState(error = "Has Not Internet", hasInternet = false))
                    } else {
                        _state.emit(WeatherState(error = "Error"))
                    }
                }
                is NetworkResult.Loading -> {
                    _state.emit(WeatherState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun currentUserLocation(latLng: LatLng) {
        _userCurrentLat.value = latLng.latitude
        _userCurrentLng.value = latLng.longitude
    }

}