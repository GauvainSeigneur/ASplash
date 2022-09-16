package com.gauvain.seigneur.data.remote

import com.gauvain.seigneur.data.model.AccessToken
import com.gauvain.seigneur.data.model.UserResponse
import retrofit2.http.*

interface UnsplashService {

    /**
     * Oauth2
     */
    @POST
    fun getAccessToken(
        @Url url: String,
        @Query("client_id") clientID: String,
        @Query("client_secret") clientSecret: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String
    ): AccessToken

    @GET("me")
    suspend fun getMe(): UserResponse

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") userName: String): UserResponse

}