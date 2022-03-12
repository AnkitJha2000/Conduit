package com.example.conduit.models.entities

data class Profile(
    val bio: String?,
    val following: Boolean,
    val image: String?,
    val username: String
)