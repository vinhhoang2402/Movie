package com.example.domain.entity

class MovieDetailResponseEntity(
    val id: Long,
    val results: MutableList<MovieDetailEntity> = mutableListOf()
)
