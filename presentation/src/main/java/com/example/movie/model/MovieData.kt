package com.example.movie.model

import java.io.Serializable

data class MovieData(
    val id: Int = 0,
    val title: String = "",
    val backdrop_path: String = "",
    val overview: String = ""
) : Serializable
