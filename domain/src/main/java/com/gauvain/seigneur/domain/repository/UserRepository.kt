package com.gauvain.seigneur.domain.repository

import com.gauvain.seigneur.domain.model.User

interface UserRepository {
    suspend fun getMe(): User
    suspend fun getUser(userName: String): User
}