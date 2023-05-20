package com.joseph.insanegrouptestapp.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import com.joseph.insanegrouptestapp.R

private val DarkColorScheme = darkColorScheme(
    secondary = PurpleGrey80,
    tertiary = Color(0xFF1F1D2B),
    primary = Color(0xFF1772FF),
    onSecondary = Color(0xFF2F3142),
    background = Color(0xFF1F1D2B),
    onSurface = Color(0xFFFFFFFF),
    onSecondaryContainer = Color(0xFFF5F5F8),
    outline = Color(0xFFF5F5F8)
)

private val LightColorScheme = lightColorScheme(
    secondary = PurpleGrey40,
    tertiary = Color(0xFFFAF9F9),
    primary = Color(0xFF1772FF),
    onSecondary = Color(0xFFFAF9F9),
    onSurface = Color(0xFF00022F),
    onSecondaryContainer = Color(0xFF6C6C70),
    outline = Color(0xFFF5F5F8),
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun InsaneGroupTestAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}