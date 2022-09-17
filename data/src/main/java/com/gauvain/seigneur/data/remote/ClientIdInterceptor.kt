package com.gauvain.seigneur.data.remote

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor which add client id as new query parameter in every request we make
 */
class ClientIdInterceptor : Interceptor {
    companion object {
        private const val CLIENT_ID_KEY = "client_id"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request
            .url
            .newBuilder()
            .addQueryParameter(CLIENT_ID_KEY, UnsplashApiSecrets.UNSPLASH_API_CLIENT_ID)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}