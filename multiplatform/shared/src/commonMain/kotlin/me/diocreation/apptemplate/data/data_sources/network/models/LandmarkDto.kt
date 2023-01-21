package me.diocreation.apptemplate.data.data_sources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LandmarkDto(
    @SerialName("id")
    var id: Int,
    @SerialName("name")
    var name: String,
    @SerialName("park")
    var park: String,
    @SerialName("state")
    var state: String,
    @SerialName("description")
    var description: String,
    @SerialName("isFavorite")
    var isFavorite: Boolean,
    @SerialName("imageName")
    var imageName: String,
    @SerialName("coordinates")
    var coordinates: CoordinatesDto
)