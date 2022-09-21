package com.gauvain.seigneur.databinder

import com.gauvain.seigneur.data.BuildConfig
import com.gauvain.seigneur.data.remote.ClientIdInterceptor
import com.gauvain.seigneur.data.remote.HeaderAccessTokenInterceptor
import com.gauvain.seigneur.data.remote.UnsplashService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UnsplashServiceModule {

    private const val BASE_URL = "https://api.unsplash.com/"
    private const val TIME_OUT_VALUE_SECONDS = 30L

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder
            .addNetworkInterceptor(ClientIdInterceptor())
            .addNetworkInterceptor(HeaderAccessTokenInterceptor())
            .connectTimeout(TIME_OUT_VALUE_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_VALUE_SECONDS, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder
                .addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashService =
        retrofit.create(UnsplashService::class.java)

}