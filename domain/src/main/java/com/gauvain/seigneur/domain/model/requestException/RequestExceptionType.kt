package com.gauvain.seigneur.domain.model.requestException

enum class RequestExceptionType {
    UNKNOWN_HOST,
    ERROR_UNKNOWN,
    CONNECTION_LOST,
    UNAUTHORIZED,
    BODY_NULL,
    SERVER_INTERNAL_ERROR,
}