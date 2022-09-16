package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.UnsplashServiceError
import com.gauvain.seigneur.domain.model.User
import com.gauvain.seigneur.domain.repository.UserRepository
import javax.inject.Inject

class GetMeUseCase @Inject constructor(
    private val userRepository: UserRepository
) : suspend () -> Outcome<User, UnsplashServiceError> {

    override suspend fun invoke(): Outcome<User, UnsplashServiceError> {
        val me = userRepository.getMe()
        return Outcome.Success(
            me
        )
    }

}