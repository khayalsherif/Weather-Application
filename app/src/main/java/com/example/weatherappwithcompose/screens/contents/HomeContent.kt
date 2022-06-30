package com.example.weatherappwithcompose.screens.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.weatherappwithcompose.components.WeatherItem
import com.example.weatherappwithcompose.model.WeatherState
import com.example.weatherappwithcompose.screens.state_contents.EmptyDataContent
import com.example.weatherappwithcompose.screens.state_contents.InternetErrorContent
import com.example.weatherappwithcompose.screens.state_contents.LoadingContent
import com.example.weatherappwithcompose.ui.theme.app_theme.AppTheme
import com.example.weatherappwithcompose.view_model.MainViewModel

@Composable
fun HomeContent(viewModel: MainViewModel) {
    viewModel.state.collectAsState().value.apply {
        val weatherState = this
        if (!weatherState.isLoading) {
            if (weatherState.hasInternet) {
                if (weatherState.error.isNotBlank()) {
                    EmptyDataContent()
                } else {
                    MainContent(weatherState)
                }
            } else {
                InternetErrorContent()
            }
        } else {
            LoadingContent()
        }
    }
}

@Composable
fun MainContent(weatherState: WeatherState) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppTheme.colors.primary,
                        MaterialTheme.colors.primaryVariant
                    )
                )
            )
            .fillMaxHeight()
    ) {
        WeatherList(weatherState)
    }
}

@Composable
fun WeatherList(weatherState: WeatherState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(weatherState.weather!!.daily) { item ->
            WeatherItem(
                weather = item, timeZone = weatherState.weather.timezone
            )
        }
    }
}
