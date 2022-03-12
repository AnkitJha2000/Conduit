package com.example.conduit.models.responses

import com.example.conduit.models.entities.Comment

data class CommentsResponse(
    val comments: List<Comment>
)