package me.diocreation.apptemplate.shared.utils

import me.diocreation.apptemplate.shared.presentation.presenters.SharedSettingsPresenter

/**Return language code based on user selection*/
fun getAppLanguage(settingsPresenter: SharedSettingsPresenter): String {
    return when (settingsPresenter.selectedLanguage.value) {
        0 -> "en"
        1 -> "es"
        2 -> "fr"
        3 -> "de"
        else -> "en"
    }
}