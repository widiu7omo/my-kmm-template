package me.diocreation.apptemplate.shared.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.diocreation.apptemplate.shared.data.data_sources.network.utils.NetworkResult
import me.diocreation.apptemplate.shared.domain.models.LandmarkData

interface LandmarksRepository {
    suspend fun fetchLandmarks(): Flow<NetworkResult<List<LandmarkData>?>>
}