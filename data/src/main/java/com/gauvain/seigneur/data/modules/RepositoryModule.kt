package com.gauvain.seigneur.data.modules

import com.gauvain.seigneur.data.remote.UnsplashService
import com.gauvain.seigneur.data.repository.UserDataRepository
import com.gauvain.seigneur.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserDataRepository(service : UnsplashService) : UserRepository =
        UserDataRepository(service)

}