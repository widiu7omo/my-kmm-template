package me.diocreation.apptemplate.data.data_sources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesDto(
    @SerialName("latitude")
    var latitude: Double,
    @SerialName("longitude")
    var longitude: Double
)