package com.volchok.rocketapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.volchok.rocketapp.library.ui.RocketColors.background
import com.volchok.rocketapp.library.ui.RocketColors.chrome700
import com.volchok.rocketapp.library.ui.RocketColors.chrome900
import com.volchok.rocketapp.library.ui.RocketColors.white

private val DarkColorPalette = darkColors(
    background = chrome700,
    primaryVariant = white
)

private val LightColorPalette = lightColors(
    background = background,
    primaryVariant = chrome900
)

@Composable
fun RocketAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()

            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }
}