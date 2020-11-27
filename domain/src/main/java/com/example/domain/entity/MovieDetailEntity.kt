package com.example.domain.entity

import androidx.room.Embedded

data class MovieDetailEntity(
    val id: String="",
    val author: String,
    val author_details: AuthorDetails?,
    val content: String,
    val created_at: String,
    val updated_at: String,
    val url: String
) {
    data class AuthorDetails(
        val name: String,
        val username: String,
        val avatar_path: String,
        val rating: Int
    )
}