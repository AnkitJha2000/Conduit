package com.example.conduit.models.entities

data class User(
    val email: String,
    val username: String,
    val bio: String?,
    val image: String?,
    val token: String?
)