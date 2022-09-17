package com.gauvain.seigneur.data.repository

import android.util.Log
import com.gauvain.seigneur.data.model.UserResponse
import com.gauvain.seigneur.data.model.request.RepositoryResult
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestError
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestResult
import com.gauvain.seigneur.domain.model.requestException.RequestExceptionType
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

abstract class BaseDataRepository {

    protected suspend fun <T, R> handleSplashApiCall(
        call: suspend () -> R,
        onSuccess: (r: RepositoryResult.Success<R>) -> T,
        onError: ((type: RequestExceptionType, message: String?) -> Unit)? = null
    ): RemoteRequestResult<T> {
        return when (val result = unSplashServiceCall(call)) {
            is RepositoryResult.Success -> RemoteRequestResult.Success(
                    onSuccess(result)
                )
            is RepositoryResult.Error -> {
                onError?.invoke(
                    result.error.exceptionType,
                    result.error.message
                )
                RemoteRequestResult.Error(
                    RemoteRequestError(
                        result.error.exceptionType,
                        result.error.message
                    )
                )
            }
        }
    }

    /**
     * Simple methods to:
     * Perform a call to CovidService
     * Handle exception during the call
     * Handle result get from server and return a RequestResult sub class
     */
    private suspend fun <T> unSplashServiceCall(call: suspend () -> T): RepositoryResult<T, RequestExceptionContent> {
        lateinit var repositoryResult: RepositoryResult<T, RequestExceptionContent>
        runCatching {
            call()
        }
            .onFailure {
                Log.d("lolilol", "failure $it")
                val exceptionContent = getRequestExceptionContent(it)
                return setError(
                    exceptionContent.exceptionType,
                    exceptionContent.message
                )
            }
            .onSuccess { response ->
                Log.d("lolilol", "onSuccess $response")
                repositoryResult = RepositoryResult.Success(response)
            }

        return repositoryResult

    }


    private fun setError(type: RequestExceptionType, message: String) =
        RepositoryResult.Error(RequestExceptionContent(type, message))

    /**
     * todo - in a mapper
     */
    private fun getRequestExceptionContent(throwable: Throwable): RequestExceptionContent =
        when (throwable) {
            is UnknownHostException ->
                RequestExceptionContent(
                    RequestExceptionType.UNKNOWN_HOST,
                    throwable.message?:"UNKNOWN_HOST"
                )
            is UnknownError -> RequestExceptionContent(
                RequestExceptionType.ERROR_UNKNOWN,
                throwable.message?:"ERROR_UNKNOWN"
            )
            is IOException -> RequestExceptionContent(
                RequestExceptionType.CONNECTION_LOST,
                throwable.message?:"CONNECTION_LOST"
            )
            is HttpException -> {
                Log.d("lolilol", "wesh ${throwable.code()} ${throwable.response()}")
                //error relative to request treatment : get the code to deliver the right message
                //todo get code, message and response to map it correclty!
                val code = throwable.code()
                RequestExceptionContent(
                    RequestExceptionType.UNAUTHORIZED, // todo to map correclty
                    throwable.message?:"chelou"
                )
            }
            else -> {
                Log.d("lolilol", "throwable $throwable")
                RequestExceptionContent(
                    RequestExceptionType.ERROR_UNKNOWN,
                    "EXCEPTION_ERROR_UNKNOWN_DESC"
                )
            }
        }

    protected data class RequestExceptionContent(
        val exceptionType: RequestExceptionType,
        val message: String
    )

}