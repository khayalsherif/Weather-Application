package com.example.weatherappwithcompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.model.Weather
import com.example.weatherappwithcompose.R
import com.example.weatherappwithcompose.ui.theme.app_theme.AppTheme

@Composable
fun WeatherItem(
    weather: Weather.Daily,
    timeZone: String
) {
    Card(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.padding(start = 16.dp, top = 32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_weather_background),
            contentDescription = ""
        )
        Row {
            Column(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Text(
                    text = "${weather.temp.day}°",
                    style = AppTheme.typography.body2,
                    color = Color.White,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = "H:16 L:18",
                    color = Color.White,
                    modifier = Modifier.padding(top = 42.dp)
                )
                Text(
                    text = timeZone,
                    color = AppTheme.colors.white,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 45.dp)
            ) {
                //This part is static because api not support image)
                Image(
                    painter = painterResource(id = R.drawable.tornado),
                    contentDescription = "Tornado", modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                )
                Text(text = "Tornado", color = Color.White)
            }
        }
    }
}
