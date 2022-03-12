package com.example.conduit.models.entities

data class Comment(
    val author: Profile,
    val body: String?,
    val createdAt: String?,
    val id: Int,
    val updatedAt: String
)