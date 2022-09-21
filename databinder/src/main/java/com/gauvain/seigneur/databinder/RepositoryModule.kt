package com.gauvain.seigneur.databinder

import com.gauvain.seigneur.data.mapper.RemoteRequestResultMapper
import com.gauvain.seigneur.data.remote.UnsplashService
import com.gauvain.seigneur.data.repository.PhotoDataRepository
import com.gauvain.seigneur.data.repository.UserDataRepository
import com.gauvain.seigneur.domain.repository.PhotoRepository
import com.gauvain.seigneur.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserDataRepository(
        remoteRequestResultMapper: RemoteRequestResultMapper,
        service: UnsplashService
    ): UserRepository =
        UserDataRepository(remoteRequestResultMapper, service)

    @Provides
    fun providePhotoDataRepository(
        remoteRequestResultMapper: RemoteRequestResultMapper,
        service: UnsplashService
    ): PhotoRepository =
        PhotoDataRepository(remoteRequestResultMapper, service)

}