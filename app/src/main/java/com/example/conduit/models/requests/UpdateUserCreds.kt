package com.example.conduit.models.requests

data class UpdateUserCreds(
    val bio: String?,
    val email: String,
    val image: String?
)