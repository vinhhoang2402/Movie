package com.example.movie.model

data class MovieDetail(
    val author: String = "",
    val author_details: AuthorDetails = AuthorDetails(),
    val content: String = "",
    val created_at: String = "",
    val id: String = "",
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