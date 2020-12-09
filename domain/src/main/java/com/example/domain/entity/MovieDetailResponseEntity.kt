package com.example.domain.entity

class MovieDetailResponseEntity(
    val id: Long,
    val results: List<MovieDetailEntity> = listOf()
)
