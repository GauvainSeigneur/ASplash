package com.gauvain.seigneur.data.repository

import android.util.Log
import com.gauvain.seigneur.data.remote.UnsplashService
import com.gauvain.seigneur.domain.repository.UserRepository
import com.gauvain.seigneur.domain.model.User
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val service: UnsplashService
) : BaseDataRepository(), UserRepository {

    override suspend fun getMe(): User {
        handleSplashApiCall(
            call = { service.getMeTwo() },
            onSuccess = {


            },
            onError = { a, b ->
                Log.d("lolilol", "a $a, b $b")
            }
        )

        return User(id = "userId")
    }

    override suspend fun getUser(userName: String): User {
        service.getUser(userName)
        return User(id = "userNameId")
    }
}