package com.gauvain.seigneur.data.mapper

import com.gauvain.seigneur.domain.model.requestException.RemoteRequestResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRequestResultMapper @Inject constructor(
    private val requestExceptionContentMapper: RequestExceptionContentMapper
) {

    fun <T> mapSuccess(t: T): RemoteRequestResult<T> = RemoteRequestResult.Success(t)

    fun mapError(throwable: Throwable): RemoteRequestResult.Error =
        RemoteRequestResult.Error(requestExceptionContentMapper.map(throwable))

}