package me.diocreation.apptemplate.shared.domain.utils

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual class NapierInit {
    actual fun init() {
        Napier.base(DebugAntilog())
    }
}