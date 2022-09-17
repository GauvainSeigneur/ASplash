package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.User
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestError
import com.gauvain.seigneur.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : suspend (String) -> Outcome<User, RemoteRequestError> {

    override suspend fun invoke(userName: String): Outcome<User, RemoteRequestError> {
        val me = userRepository.getUser(userName)
        return Outcome.Success(
            me
        )
    }

}