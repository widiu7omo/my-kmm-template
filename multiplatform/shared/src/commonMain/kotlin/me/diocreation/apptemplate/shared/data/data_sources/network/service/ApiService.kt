package me.diocreation.apptemplate.shared.data.data_sources.network.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.diocreation.apptemplate.shared.data.data_sources.network.models.LandmarkDto
import me.diocreation.apptemplate.shared.data.data_sources.network.models.RestApiResultDto

class ApiService constructor(private val httpClient: HttpClient) {
    suspend fun fetchLandmarks(): RestApiResultDto<LandmarkDto> {
        return httpClient.get(urlString = "static/landmarkData.json").body()
    }
}