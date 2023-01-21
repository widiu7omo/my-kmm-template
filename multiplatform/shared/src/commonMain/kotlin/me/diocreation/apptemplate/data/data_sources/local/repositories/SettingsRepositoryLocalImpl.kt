package me.diocreation.apptemplate.data.data_sources.local.repositories

import kotlinx.coroutines.flow.Flow
import me.diocreation.apptemplate.data.cache.multiplatform_settings.PreferencesManager
import me.diocreation.apptemplate.data.cache.multiplatform_settings.PreferencesManager.Companion.IMAGE_QUALITY_KEY
import me.diocreation.apptemplate.data.cache.multiplatform_settings.PreferencesManager.Companion.LANGUAGE_KEY
import me.diocreation.apptemplate.data.cache.multiplatform_settings.PreferencesManager.Companion.THEME_KEY
import me.diocreation.apptemplate.domain.repositories.SettingsRepository

class SettingsRepositoryLocalImpl(private val preferencesManager: PreferencesManager) :
    SettingsRepository {
    override suspend fun savePreferenceSelection(key: String, selection: Int) {
        preferencesManager.setInt(key = key, value = selection)
    }

    override suspend fun getThemePreference(): Flow<Int?> {
        return preferencesManager.getInt(key = THEME_KEY)
    }

    override suspend fun getLanguagePreference(): Flow<Int?> {
        return preferencesManager.getInt(key = LANGUAGE_KEY)
    }

    override suspend fun getImageQualityPreference(): Flow<Int?> {
        return preferencesManager.getInt(key = IMAGE_QUALITY_KEY)
    }
}