package com.example.repository

import com.example.datasource.RemoteMovieDataResource
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImp(
    private val remoteMovieDataResource: RemoteMovieDataResource
): MovieRepository {
    private fun remoteDaaResource()= remoteMovieDataResource.getMovie()

    override fun getMovie(): Single<MovieResponseEntity> {
        return remoteDaaResource()
    }

    private fun remoteDetailDataResource(id: Int)= remoteMovieDataResource.getMovieDetail(id)

    override fun getMovieDetail(id: Int): Single<MovieDetailResponseEntity> {
        return remoteDetailDataResource(id)
    }

}