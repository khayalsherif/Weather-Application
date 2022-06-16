package com.example.weatherappwithcompose.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.get_weather.GetWeatherUseCase
import com.example.domain.utils.NetworkResult
import com.example.weatherappwithcompose.base.BaseViewModel
import com.example.weatherappwithcompose.model.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext var context: Context,
    application: Application,
    private val getWeatherUseCase: GetWeatherUseCase
) : BaseViewModel() {

    private val hasInternetConnection = hasInternetConnection(application)

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

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

}