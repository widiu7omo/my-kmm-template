package me.diocreation.apptemplate.shared.di

import me.diocreation.apptemplate.shared.presentation.presenters.SharedLandmarkPresenter
import me.diocreation.apptemplate.shared.presentation.presenters.SharedMainPresenter
import me.diocreation.apptemplate.shared.presentation.presenters.SharedSettingsPresenter
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

//TODO: Used by ios KoinApplication
val Koin.settingsPresenter: SharedSettingsPresenter get() = get()
val Koin.landmarkPresenter: SharedLandmarkPresenter get() = get()