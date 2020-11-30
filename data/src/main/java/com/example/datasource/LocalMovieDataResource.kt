package com.example.datasource

import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import io.reactivex.Single

interface LocalMovieDataResource {
    fun saveMovieLocalData(movieResponseEntity: MovieResponseEntity)
    fun getMovieLocalData(): Single<MovieResponseEntity>

    fun saveMovieDetailLocalData(movieDetailResponseEntity: MovieDetailResponseEntity)
    fun getMovieDetailLocalData(movieId: Long): Single<MovieDetailResponseEntity>
}