package com.gauvain.seigneur.data.repository

import com.gauvain.seigneur.data.remote.UnsplashService
import com.gauvain.seigneur.domain.repository.UserRepository
import com.gauvain.seigneur.domain.model.User
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val service: UnsplashService
): UserRepository {

    override suspend fun getMe(): User {
        service.getMe()
        return User(id = "userId")
    }

    override suspend fun getUser(userName: String): User {
        service.getUser(userName)
        return User(id = "userNameId")
    }
}