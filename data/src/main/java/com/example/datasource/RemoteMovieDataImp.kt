package com.example.datasource

import com.example.api.MovieAPI
import com.example.common.DataConstants
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.mapper.toMovieDetailResponseEntity
import com.example.mapper.toMovieResponseEntity
import io.reactivex.Single

class RemoteMovieDataImp(private val movieAPI: MovieAPI) : RemoteMovieDataResource {
    override fun getMovie(): Single<MovieResponseEntity> {
        return movieAPI.getMovie(DataConstants.API_KEY, DataConstants.LANGUAGE, DataConstants.PAGE)
            .map {
                it.toMovieResponseEntity()
            }
    }

    override fun getMovieDetail(idMovie: Int): Single<MovieDetailResponseEntity> {
        return movieAPI.getMovieDetail(
            idMovie,
            DataConstants.API_KEY,
            DataConstants.LANGUAGE,
            DataConstants.PAGE
        )
            .map {
                it.toMovieDetailResponseEntity()
            }
    }
}