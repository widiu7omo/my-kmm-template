@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalSettingsApi::class)
package me.diocreation.apptemplate.shared.data.cache.multiplatform_settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi

class PreferencesManager constructor(private val observableSettings: ObservableSettings) {
    fun setInt(key: String, value: Int) = observableSettings.putInt(key = key, value = value)

    fun getInt(key: String) = observableSettings.getIntOrNullFlow(key = key)
    fun clearPreferences() = observableSettings.clear()

    companion object {
        const val THEME_KEY = "theme"
        const val LANGUAGE_KEY = "language"
        const val IMAGE_QUALITY_KEY = "image_quality"
    }
}