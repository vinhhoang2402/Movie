package com.example.data

data class MovieResponseData(
    var results: List<MovieData> = listOf(),
    val total_pages: Int=0
)