package com.example.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.MovieDetailData

@Dao
interface MovieDetailDAO {
    @Insert
    fun saveMovieDetail(movieDataDetail: MovieDetailData)

    @Query("SELECT * FROM MovieDetail")
    fun getMovieDetail(): List<MovieDetailData>
}