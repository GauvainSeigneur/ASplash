package com.gauvain.seigneur.data.mapper

import com.gauvain.seigneur.domain.model.requestException.RemoteRequestError
import com.gauvain.seigneur.domain.model.requestException.RequestExceptionType
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class RequestExceptionContentMapper @Inject constructor() {

    fun map(throwable: Throwable): RemoteRequestError =
        when (throwable) {
            is UnknownHostException ->
                RemoteRequestError(
                    RequestExceptionType.UNKNOWN_HOST,
                    throwable.message ?: "UNKNOWN_HOST"
                )
            is UnknownError -> RemoteRequestError(
                RequestExceptionType.ERROR_UNKNOWN,
                throwable.message ?: "ERROR_UNKNOWN"
            )
            is IOException -> RemoteRequestError(
                RequestExceptionType.CONNECTION_LOST,
                throwable.message ?: "CONNECTION_LOST"
            )
            is HttpException -> {
                mapHttpException(throwable)
            }
            else -> {
                RemoteRequestError(
                    RequestExceptionType.ERROR_UNKNOWN,
                    "EXCEPTION_ERROR_UNKNOWN_DESC"
                )
            }
        }


    //error relative to request treatment : get the code to deliver the right message
    private fun mapHttpException(httpException: HttpException): RemoteRequestError {
        val code = httpException.code()
        return RemoteRequestError(
            RequestExceptionType.UNAUTHORIZED, // todo to map correclty
            httpException.message ?: "chelou"
        )
    }
}