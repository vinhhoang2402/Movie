package com.example.domain.entity

data class GenressEntity(
    val genres: List<Genres>
){
    data class Genres(
        val id: Int,
        val name: String
    )
}