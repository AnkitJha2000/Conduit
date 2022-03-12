package com.example.conduit.models.requests

data class ArticleCreds(
    val body: String,
    val description: String,
    val tagList: List<String>,
    val title: String
)