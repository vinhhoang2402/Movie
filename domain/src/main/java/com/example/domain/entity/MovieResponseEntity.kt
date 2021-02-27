package com.example.domain.entity

data class MovieResponseEntity(
    val results: List<MovieEntity> = listOf(),
    val total_pages : Int =0
)