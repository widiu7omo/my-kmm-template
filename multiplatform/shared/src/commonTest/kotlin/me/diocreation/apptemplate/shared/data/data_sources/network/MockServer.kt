package me.diocreation.apptemplate.shared.data.data_sources.network

import com.goncalossilva.resources.Resource
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
var networkSuccess: Boolean = true

object MockServer {
    private val mockLandmarksResponse =
        Resource("src/commonTest/resources/landmarks_response.json").readText()
    private val mockLandmarksResponseError =
        Resource("src/commonTest/resources/landmarks_response_error.json").readText()
    val mockHttpClient = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        engine {
            addHandler { request ->
                when (request.url.encodedPath) {
                    "/static/landmarkData.json" -> {
                        respond(
                            content = if (networkSuccess) ByteReadChannel(mockLandmarksResponse) else ByteReadChannel(
                                mockLandmarksResponseError
                            ),
                            status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    else -> {
                        respond(
                            content = ByteReadChannel(mockLandmarksResponseError),
                            status = HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                }
            }
        }
    }

    fun expectedSuccess(isSuccess: Boolean = true) {
        networkSuccess = isSuccess
    }
}