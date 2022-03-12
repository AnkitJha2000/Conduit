package com.example.conduit.models.offlineModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.conduit.models.entities.User

@Entity(tableName = "OfflineUser")
data class OfflineDataModel(
    var userName : String,
    var bio : String,
    var favorited : String,
    var user : User
)
{
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}
