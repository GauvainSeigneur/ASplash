package com.gauvain.seigneur.data.remote

import com.gauvain.seigneur.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UnsplashService {

    @GET("me")
    suspend fun getMe(): UserResponse

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") userName: String): UserResponse

}