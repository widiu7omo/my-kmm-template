package me.diocreation.apptemplate.android.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.SystemUiController

private val DarkColorScheme = darkColorScheme(
    surface = Blue,
    onSurface = Navy,
    primary = Navy,
    onPrimary = Chartreuse
)

private val LightColorScheme = lightColorScheme(
    surface = Blue,
    onSurface = Color.White,
    primary = LightBlue,
    onPrimary = Navy
)

@Composable
fun AppBaseTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    systemUiController: SystemUiController,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        /* getting the current window by tapping into the Activity */
        val currentWindow = (view.context as? Activity)?.window
            ?: throw Exception("Not in an activity - unable to get Window reference")

        SideEffect {
            /* the default code did the same cast here - might as well use our new variable! */
            currentWindow.statusBarColor = colorScheme.primary.toArgb()
            /* accessing the insets controller to change appearance of the status bar, with 100% less deprecation warnings */
            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars =
                isDarkTheme
        }
    }
    SideEffect {
        if (isDarkTheme) {
            systemUiController.setStatusBarColor(color = DarkSurface)
        } else {
            systemUiController.setStatusBarColor(color = Surface)
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}