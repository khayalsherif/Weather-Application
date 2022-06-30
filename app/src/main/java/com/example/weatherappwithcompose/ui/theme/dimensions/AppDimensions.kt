package com.example.weatherappwithcompose.ui.theme.dimensions

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val paddingNone: Dp = 0.dp,
    val paddingSmall: Dp = 4.dp,
    val paddingMedium: Dp = 8.dp,
    val paddingLarge: Dp = 12.dp,
    val paddingXL: Dp = 16.dp,
    val paddingXXL: Dp = 24.dp,
    val paddingXXXL: Dp = 36.dp,
    val padding50: Dp = 50.dp,
    val padding55: Dp = 55.dp,
    val padding100: Dp = 100.dp
)

internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }