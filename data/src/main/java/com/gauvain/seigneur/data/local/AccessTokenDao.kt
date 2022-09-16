package com.gauvain.seigneur.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gauvain.seigneur.data.model.AccessToken

@Dao
interface AccessTokenDao {

    /**
     * Insert AccessToken in DB
     * return long (transaction id)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccessToken(inAccessToken: AccessToken): Long

    /**
     * @return AccessToken if exists, nothing if not
     */
    @Query("SELECT * FROM accesstoken")
    suspend fun getAccessToken(): AccessToken?

}
