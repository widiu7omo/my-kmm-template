package me.diocreation.apptemplate.shared.di

import io.ktor.client.engine.android.*
import me.diocreation.apptemplate.shared.domain.utils.MultiplatformSettingsWrapper
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper(context = get()).createSettings() }
    single { Android.create() }
}