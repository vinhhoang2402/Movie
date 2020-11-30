package com.example.repository

import android.util.Log
import com.example.connection.ConnectionManager
import com.example.datasource.LocalMovieDataResource
import com.example.datasource.RemoteMovieDataResource
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImp(
    private val connectionManager: ConnectionManager,
    private val localMovieDataResource: LocalMovieDataResource,
    private val remoteMovieDataResource: RemoteMovieDataResource
) : MovieRepository {
    private fun remoteDataResource() = remoteMovieDataResource.getMovie().doOnSuccess {
        saveMovieLocal(it)
    }

    private fun saveMovieLocal(movieResponseEntity: MovieResponseEntity) = localMovieDataResource
        .saveMovieLocalData(movieResponseEntity)

    override fun getMovie(): Single<MovieResponseEntity> {
        if (this.connectionManager.isConnected) {
            Log.d("hhhhh", "remote")
            return remoteDataResource()

        } else {
            Log.d("hhhhh", "local")
            return localMovieDataResource.getMovieLocalData()
        }
    }

    private fun remoteDetailDataResource(id: Int) =
        remoteMovieDataResource.getMovieDetail(id).doOnSuccess {
            saveMovieDetailLocal(it)
        }

    private fun saveMovieDetailLocal(movieDetailResponseEntity: MovieDetailResponseEntity) =
        localMovieDataResource
            .saveMovieDetailLocalData(movieDetailResponseEntity)

    override fun getMovieDetail(id: Int): Single<MovieDetailResponseEntity> {
        if (this.connectionManager.isConnected) {
            Log.d("hhhhh", "remote")
            return remoteDetailDataResource(id)

        } else {
            Log.d("hhhhh", "local")
            return localMovieDataResource.getMovieDetailLocalData(id.toLong())
        }
    }

}