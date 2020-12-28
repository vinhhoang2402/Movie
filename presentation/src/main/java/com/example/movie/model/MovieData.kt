package com.example.movie.model

import java.io.Serializable

data class MovieData(
    val id: Long = 0,
    val title: String = "",
    val backdrop_path: String = "",
    val overview: String = "",
    val vote_average: Float=0f
) : Serializable
