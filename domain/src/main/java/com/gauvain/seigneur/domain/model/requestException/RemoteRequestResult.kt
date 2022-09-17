package com.gauvain.seigneur.domain.model.requestException

sealed class RemoteRequestResult<out T> {
    data class Success<out T>(val data: T) : RemoteRequestResult<T>()
    data class Error(val error: RemoteRequestError) : RemoteRequestResult<Nothing>()
}

data class RemoteRequestError(
    val type: RequestExceptionType,
    val description: String?
)