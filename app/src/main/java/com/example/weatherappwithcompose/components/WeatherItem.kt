package com.example.weatherappwithcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherappwithcompose.ui.theme.Typography

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
