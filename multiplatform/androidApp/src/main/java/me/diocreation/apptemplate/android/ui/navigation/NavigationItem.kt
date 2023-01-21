package me.diocreation.apptemplate.android.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import me.diocreation.apptemplate.android.R

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector?
) {

    object Home : NavigationItem("home", R.string.title_home, Icons.Rounded.Home)
    object Favorites : NavigationItem("favorites", R.string.title_favorites, Icons.Rounded.Favorite)
    object Settings : NavigationItem("settings", R.string.title_settings, Icons.Rounded.Settings)
    object Details : NavigationItem("details/{movieId}/{cacheId}", R.string.title_details, null)
}