package me.diocreation.apptemplate.shared.data.data_sources.local.repositories

import com.russhwolf.settings.ExperimentalSettingsApi
import kotlinx.coroutines.flow.Flow
import me.diocreation.apptemplate.shared.data.cache.multiplatform_settings.PreferencesManager
import me.diocreation.apptemplate.shared.data.cache.multiplatform_settings.PreferencesManager.Companion.IMAGE_QUALITY_KEY
import me.diocreation.apptemplate.shared.data.cache.multiplatform_settings.PreferencesManager.Companion.LANGUAGE_KEY
import me.diocreation.apptemplate.shared.data.cache.multiplatform_settings.PreferencesManager.Companion.THEME_KEY
import me.diocreation.apptemplate.shared.domain.repositories.SettingsRepository

class SettingsRepositoryLocalImpl(private val preferencesManager: PreferencesManager) :
    SettingsRepository {
    override suspend fun savePreferenceSelection(key: String, selection: Int) {
        preferencesManager.setInt(key = key, value = selection)
    }

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun getThemePreference(): Flow<Int?> {
        return preferencesManager.getInt(key = THEME_KEY)
    }

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun getLanguagePreference(): Flow<Int?> {
        return preferencesManager.getInt(key = LANGUAGE_KEY)
    }

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun getImageQualityPreference(): Flow<Int?> {
        return preferencesManager.getInt(key = IMAGE_QUALITY_KEY)
    }
}