package me.diocreation.apptemplate.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.diocreation.apptemplate.shared.domain.repositories.SettingsRepository
import org.koin.core.component.KoinComponent

class SharedMainPresenter constructor(private val settingsRepository: SettingsRepository) :
    KoinComponent {
    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val supervisorJob = MutableStateFlow<Job?>(null)

    private val _appTheme = MutableStateFlow<Int?>(null)
    val appTheme = _appTheme.asStateFlow()

    private val _appLanguage = MutableStateFlow<Int?>(null)
    val appLanguage = _appLanguage.asStateFlow()

    init {
        getAppTheme()
        getAppLanguage()
    }

    private fun getAppTheme() {
        val job = viewModelScope.launch {
            try {
                settingsRepository.getThemePreference().collectLatest { theme ->
                    _appTheme.value = theme
                }
            } catch (e: Exception) {
                Napier.e("Error get theme: ${e.message}")
            }
        }
        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    private fun getAppLanguage() {
        val job = viewModelScope.launch {
            try {
                settingsRepository.getLanguagePreference().collectLatest { lang ->
                    _appLanguage.value = lang
                }
            } catch (e: Exception) {
                Napier.e("Error get app language : ${e.message}")
            }
        }
        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }
}