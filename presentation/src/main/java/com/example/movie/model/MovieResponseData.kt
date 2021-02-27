package com.example.movie.model

data class MovieResponseData(
    val movies: List<MovieData> = listOf(),
    val total_pages : Int =0
)