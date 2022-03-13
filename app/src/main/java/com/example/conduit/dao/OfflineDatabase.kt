package com.example.conduit.dao

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.example.conduit.models.offlineModel.OfflineArticle
import com.example.conduit.models.offlineModel.OfflineAuthor
import com.example.conduit.models.offlineModel.OfflineUser

@Database(entities = [OfflineUser::class,OfflineAuthor::class,OfflineArticle::class], version = 1, exportSchema = false)
abstract class OfflineDatabase : RoomDatabase() {
    abstract fun offlineDataDao() : OfflineDataDao
}