package com.gauvain.seigneur.data.repository

import com.gauvain.seigneur.data.mapper.RemoteRequestResultMapper
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestResult
import com.gauvain.seigneur.domain.model.requestException.RequestExceptionType

abstract class BaseDataRepository(private val remoteRequestResultMapper: RemoteRequestResultMapper) {

    /**
     * Simple methods to:
     * Perform a call to UnSplash API
     * Handle exception during the call
     * Handle result get from server and return a RemoteRequestResult sub class
     */
    protected suspend fun <T, R> handleSplashApiCall(
        call: suspend () -> R,
        onSuccess: (r: R) -> T,
        onError: ((type: RequestExceptionType, message: String?) -> Unit)? = null
    ): RemoteRequestResult<T> {
        lateinit var remoteRequestResult: RemoteRequestResult<T>
        runCatching {
            call()
        }
            .onFailure { throwable ->
                val errorResult = remoteRequestResultMapper.mapError(throwable)
                onError?.invoke(
                    errorResult.error.type,
                    errorResult.error.description
                )
                return errorResult
            }
            .onSuccess { response ->
                remoteRequestResult = remoteRequestResultMapper.mapSuccess(
                    onSuccess(response)
                )
            }

        return remoteRequestResult
    }
}