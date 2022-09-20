package com.gauvain.seigneur.asplash.paging

import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.Photo
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestError
import com.gauvain.seigneur.domain.usecase.GetPhotosUseCase
import javax.inject.Inject

class PhotoListDataSource @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
) : BasePagedListDataSource<Photo>() {

    override suspend fun getData(page: Int): Outcome<List<Photo>, RemoteRequestError> = getPhotosUseCase(page, 100)

}