package com.gauvain.seigneur.domain.model

data class UnsplashServiceError(
    val description: String,
    val type: UnsplashServiceErrorType
)
