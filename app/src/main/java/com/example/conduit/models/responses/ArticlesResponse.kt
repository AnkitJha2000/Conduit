package com.example.conduit.models.responses

import com.example.conduit.models.entities.Article

data class ArticlesResponse(
    val articles: List<Article>,
    val articlesCount: Int
)