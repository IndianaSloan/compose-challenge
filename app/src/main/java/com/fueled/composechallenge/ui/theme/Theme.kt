package com.fueled.composechallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Colors.Primary,
    primaryVariant = Colors.Primary,
    secondary = Colors.Secondary,
    onSurface = Colors.TextColor,
    onPrimary = Color.White
)

private val LightColorPalette = lightColors(
    primary = Colors.Primary,
    primaryVariant = Colors.Primary,
    secondary = Colors.Secondary,
    onSurface = Colors.TextColor,
    onPrimary = Color.White
)

@Composable
fun ComposeChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}