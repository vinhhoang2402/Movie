package com.example.datasource

import com.example.data.GenressData
import com.example.data.MovieVideoData
import com.example.domain.entity.*
import io.reactivex.Single

interface RemoteMovieDataResource {
    fun getMovie(): Single<MovieResponseEntity>
    fun getGenres(): Single<GenressEntity>
    fun getMovieRating(): Single<MovieResponseEntity>
    fun getMovieDetail(idMovie: Int): Single<MovieDetailResponseEntity>
    fun getMovieVideo(idMovie: Int): Single<MovieVideoEntityReponse>
}