package com.gauvain.seigneur.data.repository

import com.gauvain.seigneur.data.model.User
import com.gauvain.seigneur.data.remote.UnsplashService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserRepository @Inject constructor(
    private val service: UnsplashService
) {
    suspend fun getMe(): User = service.getMe()
}