package com.example.conduit.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.conduit.models.entities.User
import com.example.conduit.models.offlineModel.OfflineArticle
import com.example.conduit.models.offlineModel.OfflineAuthor
import com.example.conduit.models.offlineModel.OfflineUser

@Dao
interface OfflineDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(offlineUser : OfflineUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(offlineArticle: OfflineArticle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(offlineAuthor : OfflineAuthor)

}