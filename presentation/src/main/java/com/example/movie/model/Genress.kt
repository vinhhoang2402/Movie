package com.example.movie.model

data class Genress(
    val genres: List<Genres>
){
    data class Genres(
        val id: Int,
        val name: String
    )
}