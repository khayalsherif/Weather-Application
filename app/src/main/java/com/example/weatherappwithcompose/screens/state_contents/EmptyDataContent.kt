package com.example.weatherappwithcompose.screens.state_contents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappwithcompose.ui.theme.Typography

@Preview
@Composable
fun EmptyDataContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Info,
            contentDescription = "Warning Image",
            modifier = Modifier
                .height(50.dp)
                .width(50.dp),
            colorFilter = ColorFilter.tint(color = Color.Black)
        )
        Text(text = "Has not Data", style = Typography.h5)
    }
}