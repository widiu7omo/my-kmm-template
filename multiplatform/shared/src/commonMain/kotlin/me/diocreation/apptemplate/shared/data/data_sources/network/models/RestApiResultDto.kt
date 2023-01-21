package me.diocreation.apptemplate.shared.data.data_sources.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RestApiResultDto(
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("results")
    val landmarks: List<LandmarkDto>
)