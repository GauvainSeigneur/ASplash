package com.gauvain.seigneur.data.remote

import com.gauvain.seigneur.data.model.User
import retrofit2.http.GET

interface UnsplashService {

    @GET("me")
    suspend fun getMe(): User

}