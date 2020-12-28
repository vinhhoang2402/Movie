package com.example.datasource

import android.util.Log
import com.example.api.MovieAPI
import com.example.common.DataConstants
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.entity.MovieVideoEntity
import com.example.domain.entity.MovieVideoEntityReponse
import com.example.mapper.*
import io.reactivex.Single

class RemoteMovieDataImp(private val movieAPI: MovieAPI) : RemoteMovieDataResource {
    override fun getMovie(): Single<MovieResponseEntity> {
        return movieAPI.getMovie(DataConstants.API_KEY, DataConstants.LANGUAGE, DataConstants.PAGE)
            .map {
                Log.d("aaa",it.toString())
                it.toMovieResponseEntity()
            }
    }

    override fun getMovieRating(): Single<MovieResponseEntity> {
        return movieAPI.getMovieRating(DataConstants.API_KEY, DataConstants.LANGUAGE, DataConstants.PAGE)
            .map {
                Log.d("aaa",it.toString())
                it.toMovieResponseEntity()
            }
    }

    override fun getMovieDetail(idMovie: Int): Single<MovieDetailResponseEntity> {
        return movieAPI.getMovieDetail(
            idMovie,
            DataConstants.API_KEY,
            DataConstants.LANGUAGE
        ).map {
                it.toMovieDetailResponseEntity()
            }
    }

    override fun getMovieVideo(idMovie: Int): Single<MovieVideoEntityReponse> {
        return movieAPI.getMovieVideo(
            idMovie,
            DataConstants.API_KEY,
            DataConstants.LANGUAGE
        ).map {
            Log.d("ooooo",it.toString())
            it.toMovieVideoResponseEntity()
        }
    }
}