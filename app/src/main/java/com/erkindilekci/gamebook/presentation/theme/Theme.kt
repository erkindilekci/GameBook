package com.erkindilekci.gamebook.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Color(0xFF1976D2),
    primaryVariant = Color(0xFF004BA0),
    onPrimary = Color.White,
    secondary = Color(0xFFFFC107),
    secondaryVariant = Color(0xFFFFA000),
    onSecondary = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

private val DarkThemeColors = darkColors(
    primary = Color(0xFFD32F2F),
    primaryVariant = Color(0xFF9A0007),
    onPrimary = Color.White,
    secondary = Color(0xFFFFCDD2),
    secondaryVariant = Color(0xFFEF9A9A),
    onSecondary = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFF44336),
    onSurface = Color.White
)

@Composable
fun GameBookTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = RobotoTypography,
        shapes = Shapes,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.surface)
        ) {
            content()
        }
    }
}