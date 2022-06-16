package com.example.weatherappwithcompose.screens.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappwithcompose.permissions.PermissionDeniedContent
import com.example.weatherappwithcompose.ui.theme.Typography
import com.example.weatherappwithcompose.view_model.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@Composable
fun HomeContent(viewModel: MainViewModel) {
    Row {
        WeatherItemPreview()
    }

    viewModel.state.collectAsState().value.apply {
        if (!this.isLoading) {
            if (this.hasInternet) {
                if (this.error.isNotBlank()) {
                    println(this.error)
                } else {
                    println(this.weather!!.daily[0].temp)
                }
            } else {
                println(this.error)
            }
        }
    }

}

@Composable
fun WeatherItem(
    temperature: String,
    description: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .border(width = 1.dp, color = Color.Blue)
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .background(Color.White)
        ) {

            Column {
                Text(text = description, style = Typography.h3)
                Text(text = date)
            }
            Text(text = temperature, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Preview
@Composable
fun WeatherItemPreview() {
    WeatherItem(temperature = "38c", "Kulekli", "12/12/2022", modifier = Modifier.padding(12.dp))
}
