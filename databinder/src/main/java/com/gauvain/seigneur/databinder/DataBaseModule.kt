package com.gauvain.seigneur.databinder

import android.content.Context
import androidx.room.Room
import com.gauvain.seigneur.data.local.ASplashDataBase
import com.gauvain.seigneur.data.local.AccessTokenDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ): ASplashDataBase =
        Room.databaseBuilder(
            context,
            ASplashDataBase::class.java, "ASplashDataBase"
        )
            .build()

    @Provides
    @Singleton
    fun provideAccessToken(database: ASplashDataBase): AccessTokenDao =
        database.accessTokenDao()

}