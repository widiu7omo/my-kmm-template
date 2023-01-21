package me.diocreation.apptemplate.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import me.diocreation.apptemplate.android.ui.navigation.NavigationItem

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    backStackEntryState: State<NavBackStackEntry?>,
    navController: NavController,
    bottomNavItems: List<NavigationItem>
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .alpha(0.95F)
    ) {
        NavigationBar(
            modifier = modifier.fillMaxWidth(),
            containerColor = NavigationBarDefaults.containerColor,
            contentColor = MaterialTheme.colorScheme.contentColorFor(NavigationBarDefaults.containerColor),
            tonalElevation = NavigationBarDefaults.Elevation,
            windowInsets = NavigationBarDefaults.windowInsets
        ) {
            bottomNavItems.forEach { item ->
                val isSelected = item.route == backStackEntryState.value?.destination?.route
                NavigationBarItem(
                    icon = {
                        item.icon?.let {
                            Icon(
                                imageVector = it, contentDescription = stringResource(
                                    id = item.title
                                )
                            )
                        }
                    },
                    label = { Text(text = stringResource(id = item.title)) },
                    alwaysShowLabel = true,
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route = route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}