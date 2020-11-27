package com.example.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.MovieData
import com.example.data.MovieDetailData

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieData:MovieData): Long

    @Query("SELECT * FROM MovieTable")
    fun getMovie(): List<MovieData>

}