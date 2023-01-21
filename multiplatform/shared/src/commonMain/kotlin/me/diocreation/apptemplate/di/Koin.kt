package me.diocreation.apptemplate.di

import me.diocreation.apptemplate.presentation.presenters.SharedSettingsPresenter
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule(), commonModule(enableNetworkLogs = enableNetworkLogs))
    }

fun KoinApplication.Companion.start(): KoinApplication = initKoin { }
val Koin.settingsPresenter: SharedSettingsPresenter get() = get()