package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestError
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestResult
import com.gauvain.seigneur.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) : suspend () -> Outcome<List<Photo>, RemoteRequestError> {

    override suspend fun invoke(): Outcome<List<Photo>, RemoteRequestError> {
        return when (val result = photoRepository.getPhotos()) {
            is RemoteRequestResult.Success -> {
                Outcome.Success(result.data)
            }
            is RemoteRequestResult.Error -> {
                Outcome.Error(result.error)
            }
        }
    }
}