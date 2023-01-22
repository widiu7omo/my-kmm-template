package me.diocreation.apptemplate.shared.data.data_sources.network.repositories

import io.ktor.client.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import me.diocreation.apptemplate.shared.data.data_sources.network.MockServer
import me.diocreation.apptemplate.shared.data.data_sources.network.service.ApiService
import me.diocreation.apptemplate.shared.data.data_sources.network.utils.NetworkResult
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LandmarkRepositoryTest {
    private lateinit var mockHttpClient: HttpClient
    private lateinit var apiServiceTest: ApiService

    private lateinit var landmarkRepository: LandmarksRepositoryNetworkImp

    @BeforeTest
    fun setup() {
        mockHttpClient = MockServer.mockHttpClient
        apiServiceTest = ApiService(httpClient = mockHttpClient)
        landmarkRepository = LandmarksRepositoryNetworkImp(apiService = apiServiceTest)
    }

    @OptIn(DelicateCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        GlobalScope.launch {
            delay(2000)
            mockHttpClient.close()
        }
    }

    @Test
    fun `get landmarks returns error on failure`() = runTest {
        MockServer.expectedSuccess(isSuccess = false)
        val response = landmarkRepository.fetchLandmarks().first()
        assertTrue { response is NetworkResult.Error }
    }

    @Test
    fun `get landmarks return success`() = runTest {
        MockServer.expectedSuccess(isSuccess = true)
        val response = landmarkRepository.fetchLandmarks().first()
        assertTrue {
            response is NetworkResult.Success
        }
    }
}