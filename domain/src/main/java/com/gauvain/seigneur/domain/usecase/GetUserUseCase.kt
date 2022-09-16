package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.UnsplashServiceError
import com.gauvain.seigneur.domain.model.User
import com.gauvain.seigneur.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : suspend (String) -> Outcome<User, UnsplashServiceError> {

    override suspend fun invoke(userName: String): Outcome<User, UnsplashServiceError> {
        val me = userRepository.getUser(userName)
        return Outcome.Success(
            me
        )
    }

}