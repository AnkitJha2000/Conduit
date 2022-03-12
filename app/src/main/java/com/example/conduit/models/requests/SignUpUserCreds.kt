package com.example.conduit.models.requests

data class SignUpUserCreds(
    val email: String,
    val password: String,
    val username: String
)