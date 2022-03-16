package com.example.conduit.models.requests

data class SignUpUserCreds(
    val username: String,
    val email: String,
    val password: String
)