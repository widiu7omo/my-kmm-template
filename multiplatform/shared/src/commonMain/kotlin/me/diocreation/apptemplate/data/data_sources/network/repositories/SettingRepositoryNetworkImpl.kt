package me.diocreation.apptemplate.data.data_sources.network.repositories

import kotlinx.coroutines.flow.Flow
import me.diocreation.apptemplate.domain.repositories.SettingsRepository

//TODO: Inject to koin DI
class SettingRepositoryNetworkImpl : SettingsRepository {
    override suspend fun savePreferenceSelection(key: String, selection: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getThemePreference(): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override suspend fun getLanguagePreference(): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override suspend fun getImageQualityPreference(): Flow<Int?> {
        TODO("Not yet implemented")
    }
}