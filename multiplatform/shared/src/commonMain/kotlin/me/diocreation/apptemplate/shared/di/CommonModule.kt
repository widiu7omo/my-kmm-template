package me.diocreation.apptemplate.shared.di

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.diocreation.apptemplate.shared.data.cache.multiplatform_settings.PreferencesManager
import me.diocreation.apptemplate.shared.data.data_sources.local.repositories.SettingsRepositoryLocalImpl
import me.diocreation.apptemplate.shared.data.data_sources.network.service.ApiService
import me.diocreation.apptemplate.shared.domain.repositories.SettingsRepository
import me.diocreation.apptemplate.shared.domain.utils.Constants.BASE_URL
import me.diocreation.apptemplate.shared.domain.utils.Constants.BASE_URL_SECURE
import me.diocreation.apptemplate.shared.presentation.presenters.SharedMainPresenter
import me.diocreation.apptemplate.shared.presentation.presenters.SharedSettingsPresenter
import me.diocreation.apptemplate.shared.utils.getAppLanguage
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module


fun commonModule(enableNetworkLogs: Boolean) = module {
//    Multiplatform Settings
    single { PreferencesManager(observableSettings = get()) }

    single {
        HttpClient(get()) {
            defaultRequest {
                url {
                    host = BASE_URL
                    url { protocol = if (BASE_URL_SECURE) URLProtocol.HTTPS else URLProtocol.HTTP }
                    parameters.append("api_key", "-")
                    parameters.append("language", getAppLanguage(settingsPresenter = get()))

                }
            }
            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.HEADERS
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.e(tag = "Http Client", message = message)
                        }
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    //TODO: ADD YOUR DEPENDENCIES HERE
    single {
        ApiService(httpClient = get())
    }
    single<SettingsRepository> { SettingsRepositoryLocalImpl(preferencesManager = get()) }
    single { SharedSettingsPresenter(settingsRepository = get()) }
    single { SharedMainPresenter(settingsRepository = get()) }

}