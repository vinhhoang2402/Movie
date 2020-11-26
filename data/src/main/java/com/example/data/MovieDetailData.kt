package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieDetail")
class MovieDetailData(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    @PrimaryKey
    val id: String,
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