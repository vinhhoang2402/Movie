package com.example.movie.model

import com.example.domain.entity.MovieEntity

data class MovieResponseData(
    val movies: MutableList<MovieData> = mutableListOf()
)