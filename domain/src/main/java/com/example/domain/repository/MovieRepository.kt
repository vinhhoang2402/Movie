package com.example.domain.repository

import com.example.domain.entity.*
import io.reactivex.Single

interface MovieRepository {
    fun getGenres(): Single<GenressEntity>
    fun getMovie(): Single<MovieResponseEntity>
    fun getMovieRating(): Single<MovieResponseEntity>
    fun getMovieDetail(id: Int): Single<MovieDetailResponseEntity>
    fun getMovieVideo(id: Int): Single<MovieVideoEntityReponse>
}