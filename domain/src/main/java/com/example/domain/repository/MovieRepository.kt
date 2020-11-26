package com.example.domain.repository

import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import io.reactivex.Single

interface MovieRepository {
    fun getMovie(): Single<MovieResponseEntity>
    fun getMovieDetail(id: Int): Single<MovieDetailResponseEntity>
}