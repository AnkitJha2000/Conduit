package com.example.conduit.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface OfflineDataDao {

    fun upsert()

}