package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieTable")
data class MovieData(
    @PrimaryKey
    val id: Long = 0L,
    var title: String,
    var backdrop_path: String,
    var overview: String,
    val vote_average: Float
)