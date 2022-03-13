package com.example.conduit.models.offlineModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Authors")
data class OfflineAuthor(
    val bio: String?,
    val following: Boolean,
    val image: String?,
    @PrimaryKey val username: String
)