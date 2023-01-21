package me.diocreation.apptemplate.domain.utils

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual class NapierInit {
    actual fun init() {
        Napier.base(DebugAntilog())
    }
}