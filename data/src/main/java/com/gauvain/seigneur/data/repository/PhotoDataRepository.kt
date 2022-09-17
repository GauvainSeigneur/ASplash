package com.gauvain.seigneur.data.repository

import android.util.Log
import com.gauvain.seigneur.data.remote.UnsplashService
import com.gauvain.seigneur.domain.model.Photo
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestResult
import com.gauvain.seigneur.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoDataRepository @Inject constructor(
    private val service: UnsplashService
) : BaseDataRepository(), PhotoRepository {

    override suspend fun getPhotos(): RemoteRequestResult<List<Photo>> {
        return handleSplashApiCall(
            call = {service.getPhotos()},
            onSuccess = { result ->
                Log.d("lolilol", "result $result")
                result.data.map { photoResponse ->
                    Photo(id = photoResponse.id)
                }
            }
        )
    }
}