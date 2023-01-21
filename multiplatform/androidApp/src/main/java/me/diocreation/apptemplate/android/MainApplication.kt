package me.diocreation.apptemplate.android

import me.diocreation.apptemplate.android.di.presentationModule
import me.diocreation.apptemplate.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import me.diocreation.apptemplate.shared.domain.utils.NapierInit
import org.koin.core.logger.Level

import android.app.Application as BaseApplication

class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        val appModules = listOf(presentationModule)
        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@MainApplication)
            modules(appModules)
        }
        if (BuildConfig.DEBUG) NapierInit().init()
    }
}