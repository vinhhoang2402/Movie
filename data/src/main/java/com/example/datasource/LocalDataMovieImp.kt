package com.example.datasource

import android.util.Log
import com.example.db.MovieDatabase
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.mapper.toMovieData
import com.example.mapper.toMovieDetailData
import com.example.mapper.toMovieDetailEntity

import com.example.mapper.toMovieEntity
import io.reactivex.Single

class LocalDataMovieImp(
    database: MovieDatabase
) : LocalMovieDataResource {
    private val dao = database.getMovieDAO()
    private val daoDetail = database.getMovieDetailDAO()
    override fun saveMovieLocalData(movieResponseEntity: MovieResponseEntity) {
        movieResponseEntity.results.map {
            Log.d("dataLocal", it.toMovieData().toString())
            dao.saveMovie(it.toMovieData())
        }
    }

    override fun getMovieLocalData(): Single<MovieResponseEntity> {
        Log.d("vvvvMovie", "it.toMovieEntity().toString()")
        return Single.just(
            MovieResponseEntity(dao.getMovie().map {
                Log.d("vvvvMovie", it.toMovieEntity().toString())
                it.toMovieEntity()
            })
        )
    }

    override fun saveMovieDetailLocalData(movieDetailResponseEntity: MovieDetailResponseEntity) {
        val movieId = movieDetailResponseEntity.id
        movieDetailResponseEntity.results.forEach {
            Log.d(
                "vvvvSaveDetail",
                daoDetail.saveMovieDetail(it.toMovieDetailData(movieId)).toString()
            )
            daoDetail.saveMovieDetail(it.toMovieDetailData(movieId))
        }
    }

    override fun getMovieDetailLocalData(movieId: Long): Single<MovieDetailResponseEntity> {
        return Single.just(
            MovieDetailResponseEntity(movieId, daoDetail.getMovieDetail(movieId).map {
                it.toMovieDetailEntity()
            })
        )
    }
}