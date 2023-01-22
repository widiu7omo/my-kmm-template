package me.diocreation.apptemplate.shared.data.data_sources.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RestApiResultDto<T>(
    @SerialName("success") val success: Boolean = false,
    @SerialName("results") val results: List<T>?,//Don't fill with default value, because it won't catch an error
    @SerialName("message") val message: String
)