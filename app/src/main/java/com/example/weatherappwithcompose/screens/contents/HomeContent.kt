package com.example.weatherappwithcompose.screens.contents

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappwithcompose.components.WeatherItem
import com.example.weatherappwithcompose.screens.state_contents.EmptyDataContent
import com.example.weatherappwithcompose.screens.state_contents.InternetErrorContent
import com.example.weatherappwithcompose.screens.state_contents.LoadingContent
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
                    Scaffold { contentPadding ->
                        Box(
                            modifier = Modifier
                                .padding(contentPadding)
                        ) {
                            WeatherItem(
                                temperature = "18C",
                                description = weatherState.weather!!.daily[0].temp.day.toString(),
                                date = "11/01/2021"
                            )
                        }
                    }
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
fun WeatherList() {
}

@Preview
@Composable
fun WeatherItemPreview() {
    WeatherItem(temperature = "38c", "Windy", "12/12/2022", modifier = Modifier.padding(12.dp))
}

@Preview
@Composable
fun WeatherListPreview() {
    WeatherList()
}
