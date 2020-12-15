package com.example.datasource

import com.example.data.MovieVideoData
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.entity.MovieVideoEntity
import com.example.domain.entity.MovieVideoEntityReponse
import io.reactivex.Single

interface RemoteMovieDataResource {
    fun getMovie(): Single<MovieResponseEntity>
    fun getMovieDetail(idMovie: Int): Single<MovieDetailResponseEntity>
    fun getMovieVideo(idMovie: Int): Single<MovieVideoEntityReponse>
}