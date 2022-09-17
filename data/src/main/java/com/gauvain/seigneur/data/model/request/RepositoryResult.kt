package com.gauvain.seigneur.data.model.request

sealed class RepositoryResult<out T, out E : Any> {
    data class Success<out T>(val data: T) : RepositoryResult<T, Nothing>()
    data class Error<out E : Any>(val error: E) : RepositoryResult<Nothing, E>()
}