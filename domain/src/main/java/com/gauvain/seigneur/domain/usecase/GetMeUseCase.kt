package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.User
import com.gauvain.seigneur.domain.model.requestException.RemoteRequestError
import com.gauvain.seigneur.domain.repository.UserRepository
import javax.inject.Inject

class GetMeUseCase @Inject constructor(
    private val userRepository: UserRepository
) : suspend () -> Outcome<User, RemoteRequestError> {

    override suspend fun invoke(): Outcome<User, RemoteRequestError> {
        val me = userRepository.getMe()
        return Outcome.Success(
            me
        )
    }

}