package com.example.datasource

import android.util.Log
import com.example.api.MovieAPI
import com.example.common.DataConstants
import com.example.domain.entity.*
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

    override fun getGenres(): Single<GenressEntity> {
        Log.d("genres","hhhhhhhhhh")
        return movieAPI.getGenres(DataConstants.API_KEY, DataConstants.LANGUAGE)
            .map {
                Log.d("genres",it.toString())
                it.toGenresEntity()
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