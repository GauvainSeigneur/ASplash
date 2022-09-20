package com.gauvain.seigneur.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class HeaderAccessTokenInterceptor : Interceptor {

    companion object {
        private const val API_VERSION_KEY = "Accept-Version"
        private const val API_VERSION = "v1"
        private const val API_PRIVATE_ROUTE_AUTHORIZATION_KEY = "Authorization"
        private const val API_PRIVATE_ROUTE_AUTHORIZATION_VALUE_TYPE = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(API_VERSION_KEY, API_VERSION)
        val tokenValue: String? = null
        return if (!tokenValue.isNullOrEmpty()) {
            builder.addHeader(
                API_PRIVATE_ROUTE_AUTHORIZATION_KEY,
                "$API_PRIVATE_ROUTE_AUTHORIZATION_VALUE_TYPE $tokenValue")
            chain.proceed(builder.build())
        } else {
            // do nothing
            chain.proceed(builder.build())
        }
    }

}
