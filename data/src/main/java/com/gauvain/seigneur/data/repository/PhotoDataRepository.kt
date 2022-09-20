package com.gauvain.seigneur.data.repository

import android.util.Log
import com.gauvain.seigneur.data.mapper.RemoteRequestResultMapper
import com.gauvain.seigneur.data.remote.UnsplashService
import com.gauvain.seigneur.domain.model.Photo
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestResult
import com.gauvain.seigneur.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoDataRepository @Inject constructor(
    remoteRequestResultMapper: RemoteRequestResultMapper,
    private val service: UnsplashService,
) : BaseDataRepository(remoteRequestResultMapper), PhotoRepository {

    override suspend fun getPhotos(): RemoteRequestResult<List<Photo>> {
        return handleSplashApiCall(
            call = {service.getPhotos()},
            onSuccess = { photos ->
                Log.d("PhotoDataRepository", "photos $photos")
                photos.map { photoResponse ->
                    Photo(id = photoResponse.id)
                }
            }
        )
    }
}