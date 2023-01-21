package me.diocreation.apptemplate.android.utils

import android.content.Context
import java.util.*


class LocaleManager {
    fun setLocale(context: Context, language: String) {
        val locale = Locale(language)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.createConfigurationContext(configuration)
        context.resources
    }
}