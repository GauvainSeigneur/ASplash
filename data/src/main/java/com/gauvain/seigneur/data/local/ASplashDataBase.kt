package com.gauvain.seigneur.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gauvain.seigneur.data.model.AccessToken

@Database(entities = [AccessToken::class], version = 1)
abstract class ASplashDataBase : RoomDatabase() {
     // DAO
    abstract fun accessTokenDao(): AccessTokenDao
}


