package com.gauvain.seigneur.data.remote

import com.gauvain.seigneur.data.model.AccessToken
import com.gauvain.seigneur.data.model.photo.Photo
import com.gauvain.seigneur.data.model.UserResponse
import retrofit2.Call
import retrofit2.Response
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
        @Query("grant_type") grantType: String,
    ): AccessToken

    @GET("me")
    fun getMe(): Call<UserResponse>

    @GET("me")
    suspend fun getMeTwo(): UserResponse

    @GET("users/{username}")
    fun getUser(
        @Path("username") userName: String,
    ): UserResponse

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): List<Photo>
}