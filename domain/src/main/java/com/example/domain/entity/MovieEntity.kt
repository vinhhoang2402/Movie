package com.example.domain.entity

data class MovieEntity(
    val id : Long=0,
    val title: String="",
    val backdrop_path: String="",
    val overview: String
)