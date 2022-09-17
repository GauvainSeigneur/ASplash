package com.gauvain.seigneur.domain.repository

import com.gauvain.seigneur.domain.model.Photo
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestResult

interface PhotoRepository {
    suspend fun getPhotos(): RemoteRequestResult<List<Photo>>
}