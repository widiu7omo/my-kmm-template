package me.diocreation.apptemplate.android.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.diocreation.apptemplate.android.R
import me.diocreation.apptemplate.android.ui.components.BottomNavBar
import me.diocreation.apptemplate.android.ui.navigation.Navigation
import me.diocreation.apptemplate.android.ui.navigation.NavigationItem
import me.diocreation.apptemplate.android.ui.theme.AppBaseTheme
import me.diocreation.apptemplate.android.utils.LocaleManager
import me.diocreation.apptemplate.shared.presentation.presenters.SharedMainPresenter
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val localeUtil by inject<LocaleManager>()
    private val viewModel by inject<SharedMainPresenter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val currentTheme = viewModel.appTheme.collectAsState().value
            val isDarkTheme =
                when (stringArrayResource(id = R.array.theme_entries)[currentTheme ?: 0]) {
                    "light_theme" -> false
                    "dark_theme" -> true
                    else -> isSystemInDarkTheme()
                }
            val currentLanguage = viewModel.appLanguage.collectAsState().value
            val languageEntry =
                stringArrayResource(id = R.array.language_entries)[currentLanguage ?: 0]
            localeUtil.setLocale(context = LocalContext.current, language = languageEntry)

            AppBaseTheme(isDarkTheme = isDarkTheme, systemUiController = systemUiController) {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    val topLevelDestination = listOf(
        NavigationItem.Home
        //Add another top level destination here
    )
    val isTopLevelDestination =
        navController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestination.map { it.route }
    val backStackEntryState = navController.currentBackStackEntryAsState()
    Scaffold(bottomBar = {
        if (isTopLevelDestination) {
            BottomNavBar(
                navController = navController,
                backStackEntryState = backStackEntryState,
                bottomNavItems = topLevelDestination
            )
        }
    }) {
        Navigation(navController = navController)
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    val systemUiController = rememberSystemUiController()
    AppBaseTheme(systemUiController = systemUiController) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            MainScreen()
        }
    }
}
