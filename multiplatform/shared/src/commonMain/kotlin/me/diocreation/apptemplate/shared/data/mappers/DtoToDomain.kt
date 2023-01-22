package me.diocreation.apptemplate.shared.data.mappers

import me.diocreation.apptemplate.shared.data.data_sources.network.models.CoordinatesDto
import me.diocreation.apptemplate.shared.data.data_sources.network.models.LandmarkDto
import me.diocreation.apptemplate.shared.domain.models.Coordinates
import me.diocreation.apptemplate.shared.domain.models.LandmarkData

fun LandmarkDto.toDomain(): LandmarkData {
    return LandmarkData(
        id = this.id,
        name = this.name,
        park = this.park,
        state = this.state,
        description = this.description,
        isFavorite = this.isFavorite,
        imageName = this.imageName,
        coordinates = this.coordinates.toDomain()
    )
}

fun CoordinatesDto.toDomain(): Coordinates {
    return Coordinates(latitude = this.latitude, longitude = this.longitude)
}