package me.diocreation.apptemplate.android.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.diocreation.apptemplate.android.ui.theme.DarkPrimaryColor
import me.diocreation.apptemplate.android.ui.theme.PrimaryColor

sealed class NetState {
    object NetworkOn : NetState()
    object NetworkOff : NetState()
    object NetworkUnknown : NetState()
}

@Composable
fun ChangeSystemBarOnNetChange(
    key: NetState,
    systemUiController: SystemUiController,
    darkTheme: Boolean = isSystemInDarkTheme(),
    color: Color = MaterialTheme.colorScheme.surface
) {
    LaunchedEffect(key1 = key) {
        when (key) {
            NetState.NetworkOff -> {
                this.launch {
                    systemUiController.setSystemBarsColor(color = if (darkTheme) DarkPrimaryColor else PrimaryColor)
                    delay(3000L)
                    systemUiController.setSystemBarsColor(color = color)
                }
            }
            NetState.NetworkOn -> {}
            NetState.NetworkUnknown -> {}
        }
    }
}
