package me.diocreation.apptemplate.android.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import me.diocreation.apptemplate.shared.presentation.presenters.SharedLandmarkPresenter
import me.diocreation.apptemplate.shared.presentation.presenters.SharedSettingsPresenter
import org.koin.androidx.compose.get

@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedLandmarkPresenter = get()) {
    val scrollState = rememberScrollState()
    val landmarks = sharedViewModel.landmarks.collectAsState().value
    val error = sharedViewModel.error.collectAsState().value
    println(landmarks.toString())
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This is HomeScreen")
            Text(text = landmarks.toString())
            Text(text = error.toString())
        }
    }
}