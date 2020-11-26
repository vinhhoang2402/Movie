package com.example.repository

import android.util.Log
import com.example.datasource.LocalMovieDataResource
import com.example.datasource.RemoteMovieDataResource
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImp(
    private val localMovieDataResource: LocalMovieDataResource,
    private val remoteMovieDataResource: RemoteMovieDataResource
) : MovieRepository {
    private fun remoteDaaResource() = remoteMovieDataResource.getMovie().doOnSuccess {
        //Log.d("aaaaaaaaaaaa", it.toString())
        saveMovieLocal(it)
    }

    private fun saveMovieLocal(movieResponseEntity: MovieResponseEntity) = localMovieDataResource
        .saveMovieLocalData(movieResponseEntity)

    override fun getMovie(): Single<MovieResponseEntity> {
        return remoteDaaResource()
    }

    private fun remoteDetailDataResource(id: Int) = remoteMovieDataResource.getMovieDetail(id).doOnSuccess {
        saveMovieDetailLocal(it)
    }

    private fun saveMovieDetailLocal(movieDetailResponseEntity: MovieDetailResponseEntity) = localMovieDataResource
        .saveMovieDetailLocalData(movieDetailResponseEntity)

    override fun getMovieDetail(id: Int): Single<MovieDetailResponseEntity> {
        return remoteDetailDataResource(id)
    }

}