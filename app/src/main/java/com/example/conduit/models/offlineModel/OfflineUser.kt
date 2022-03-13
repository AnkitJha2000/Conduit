package com.example.conduit.models.offlineModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OfflineUser")
data class OfflineUser(
    val bio: String?,
    val email: String,
    val image: String?,
    val token: String?,
    @PrimaryKey val username: String
)
