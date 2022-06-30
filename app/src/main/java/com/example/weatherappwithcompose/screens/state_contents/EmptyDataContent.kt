package com.example.weatherappwithcompose.screens.state_contents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherappwithcompose.ui.theme.app_theme.AppTheme

@Preview
@Composable
fun EmptyDataContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.white),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Info,
            contentDescription = "Empty Image",
            modifier = Modifier
                .height(AppTheme.dimensions.padding50)
                .width(AppTheme.dimensions.padding50),
            colorFilter = ColorFilter.tint(color = AppTheme.colors.black)
        )
        Text(text = "Has not Data", style = AppTheme.typography.h5)
    }
}