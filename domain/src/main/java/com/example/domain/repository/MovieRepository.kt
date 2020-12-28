package com.example.domain.repository

import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.entity.MovieVideoEntity
import com.example.domain.entity.MovieVideoEntityReponse
import io.reactivex.Single

interface MovieRepository {
    fun getMovie(): Single<MovieResponseEntity>
    fun getMovieRating(): Single<MovieResponseEntity>
    fun getMovieDetail(id: Int): Single<MovieDetailResponseEntity>
    fun getMovieVideo(id: Int): Single<MovieVideoEntityReponse>
}