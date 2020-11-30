package com.example.domain.entity

data class MovieResponseEntity(
    val results: MutableList<MovieEntity> = mutableListOf()
)