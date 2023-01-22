package me.diocreation.apptemplate.shared.data.data_sources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(

    @SerialName("status_code")
    val statusCode: Int?,

    @SerialName("status_message")
    val statusMessage: String?,

    @SerialName("success")
    val success: Boolean?
)
