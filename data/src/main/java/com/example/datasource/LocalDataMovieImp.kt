package com.example.datasource

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
    private val daoDetail=database.getMovieDetail()
    override fun saveMovieLocalData(movieResponseEntity: MovieResponseEntity) {
        movieResponseEntity.results.map {
            dao.saveMovie(it.toMovieData())
        }
    }

    override fun getMovieLocalData(): Single<MovieResponseEntity> {
        return Single.just(
            MovieResponseEntity(dao.getMovie().map {
                it.toMovieEntity()
            }.toMutableList())
        )
    }

    override fun saveMovieDetailLocalData(movieDetailResponseEntity: MovieDetailResponseEntity) {
        movieDetailResponseEntity.results.map {
            daoDetail.saveMovieDetail(it.toMovieDetailData())
        }
    }

    override fun getMovieDetailLocalData(): Single<MovieDetailResponseEntity> {
        return  Single.just(
            MovieDetailResponseEntity(daoDetail.getMovieDetail().map {
                it.toMovieDetailEntity()
            }.toMutableList())
        )
    }
}