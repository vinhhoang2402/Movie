package com.example.datasource

import com.example.domain.entity.MovieEntity
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import io.reactivex.Single

interface RemoteMovieDataResource{
    fun getMovie(): Single<MovieResponseEntity>
    fun getMovieDetail(idMovie: Int): Single<MovieDetailResponseEntity>
}