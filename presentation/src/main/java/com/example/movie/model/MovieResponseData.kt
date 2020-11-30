package com.example.movie.model

data class MovieResponseData(
    val movies: MutableList<MovieData> = mutableListOf()
)