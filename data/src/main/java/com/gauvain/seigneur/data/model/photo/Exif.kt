package com.gauvain.seigneur.data.model.photo

data class Exif(
    val make: String?,
    val model: String?,
    val exposure_time: Long?,
    val aperture : Long?,
    val focal_length: Int?,
    val iso: Int?
)