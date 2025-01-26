package com.example.sougna.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = MarketplacePurple,
    secondary = ActionOrange,
    tertiary = TrustTeal,
    background = LightBackground,
    surface = CardSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = DarkText,
    onSurface = DarkText,
    error = ErrorRed,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = DeepPurple,
    secondary = MutedOrange,
    tertiary = DeepTeal,
    background = DarkBackground,
    surface = DarkCardSurface,
    onPrimary = LightText,
    onSecondary = LightText,
    onBackground = LightText,
    onSurface = LightText,
    error = ErrorRed,
    onError = Color.White
)

@Composable
fun SougnaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Choose color scheme based on theme mode
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}