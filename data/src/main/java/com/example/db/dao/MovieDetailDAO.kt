package com.example.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.MovieDetailData

@Dao
interface MovieDetailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieDetail(movieDataDetail: MovieDetailData): Long

    @Query("SELECT * FROM MovieDetail where movieId=:movieId")
    fun getMovieDetail(movieId: Long): List<MovieDetailData>
}