package me.diocreation.apptemplate.shared.data.data_sources.network.utils

import io.ktor.client.plugins.*
import io.ktor.util.*
import io.ktor.utils.io.*
import kotlinx.serialization.decodeFromString
import me.diocreation.apptemplate.shared.data.data_sources.network.models.ApiError

@OptIn(InternalAPI::class)
suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T?): NetworkResult<T?> {
    return try {
        val result = apiCall.invoke()
        NetworkResult.Success(data = result)
    } catch (e: RedirectResponseException) { // 3xx errors
        val networkError = getError(responseContent = e.response.content)
        println(networkError)
        NetworkResult.Error(
            errorCode = networkError.statusCode ?: e.response.status.value,
            errorMessage = networkError.statusMessage ?: e.message
        )
    } catch (e: ClientRequestException) { // 4xx errors
        val networkError = getError(responseContent = e.response.content)
        println(networkError)
        NetworkResult.Error(
            errorCode = networkError.statusCode ?: e.response.status.value,
            errorMessage = networkError.statusMessage ?: e.message
        )
    } catch (e: ServerResponseException) { // 5xx errors
        val networkError = getError(responseContent = e.response.content)
        println(networkError)
        NetworkResult.Error(
            errorCode = networkError.statusCode ?: e.response.status.value,
            errorMessage = networkError.statusMessage ?: e.message
        )
    } catch (e: Exception) {
        println(e)
        NetworkResult.Error(
            errorCode = 0,
            errorMessage = e.message ?: "An unknown error occurred"
        )
    }
}

suspend fun getError(responseContent: ByteReadChannel): ApiError {
    return kotlinx.serialization.json.Json.decodeFromString(string = responseContent.toString())
    // throw IllegalArgumentException("Not a parsable error")
}