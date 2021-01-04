package com.example.data

data class GenressData(
    val genres: List<Genres>
){
    data class Genres(
        val id: Int,
        val name: String
    )
}