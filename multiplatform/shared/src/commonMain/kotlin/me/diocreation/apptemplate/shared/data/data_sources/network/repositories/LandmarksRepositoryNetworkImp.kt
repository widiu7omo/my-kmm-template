package me.diocreation.apptemplate.shared.data.data_sources.network.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.diocreation.apptemplate.shared.data.data_sources.network.models.ApiError
import me.diocreation.apptemplate.shared.data.data_sources.network.models.LandmarkDto
import me.diocreation.apptemplate.shared.data.data_sources.network.models.RestApiResultDto
import me.diocreation.apptemplate.shared.data.data_sources.network.service.ApiService
import me.diocreation.apptemplate.shared.data.data_sources.network.utils.NetworkResult
import me.diocreation.apptemplate.shared.data.data_sources.network.utils.safeApiCall
import me.diocreation.apptemplate.shared.data.mappers.toDomain
import me.diocreation.apptemplate.shared.domain.models.LandmarkData
import me.diocreation.apptemplate.shared.domain.repositories.LandmarksRepository

class LandmarksRepositoryNetworkImp constructor(private val apiService: ApiService) :
    LandmarksRepository {
    override suspend fun fetchLandmarks(): Flow<NetworkResult<List<LandmarkData>?>> {
        val networkResponse = safeApiCall {
            apiService.fetchLandmarks().results?.map { it.toDomain() }
        }
        return flowOf(networkResponse)
    }
}