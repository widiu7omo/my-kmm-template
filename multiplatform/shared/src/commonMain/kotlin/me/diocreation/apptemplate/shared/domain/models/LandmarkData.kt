package me.diocreation.apptemplate.shared.domain.models

data class LandmarkData(
    var id: Int,
    var name: String,
    var park: String,
    var state: String,
    var description: String,
    var isFavorite: Boolean,
    var imageName: String,
    var coordinates: Coordinates
)

data class Coordinates(
    var latitude: Double,
    var longitude: Double
)