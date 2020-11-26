package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull
@Entity(tableName = "MovieTable")
data class MovieData(
    @PrimaryKey
    val id: Int=0,
    val title: String,
    val backdrop_path: String,
    val overview: String
)