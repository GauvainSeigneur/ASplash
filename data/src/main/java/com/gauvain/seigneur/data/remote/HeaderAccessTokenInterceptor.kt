package com.gauvain.seigneur.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class HeaderAccessTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Accept-Version", "v1")
        val tokenValue = "tokenValue"
        builder.addHeader("Authorization", "Bearer $tokenValue")
        return chain.proceed(builder.build())
    }
}
