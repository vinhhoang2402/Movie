package com.example.movie.model

data class MovieDetail(
    val author: String = "",
    val author_details: AuthorDetails,
    val content: String = "",
    val created_at: String = "",
    val id: Int=0,
    val updated_at: String = "",
    val url: String = ""
) {
    data class AuthorDetails(
        val name: String = "",
        val username: String = "",
        val avatar_path: String = "",
        val rating: Int = 0
    )
}