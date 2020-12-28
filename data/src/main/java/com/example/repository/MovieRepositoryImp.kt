package com.example.repository

import android.util.Log
import com.example.connection.ConnectionManager
import com.example.datasource.LocalMovieDataResource
import com.example.datasource.RemoteMovieDataResource
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.entity.MovieVideoEntityReponse
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
    private fun remoteDataRatingResource() = remoteMovieDataResource.getMovieRating()

    private fun remoteVideoDataSource(id:Int) = remoteMovieDataResource.getMovieVideo(id)

    private fun saveMovieLocal(movieResponseEntity: MovieResponseEntity) = localMovieDataResource
        .saveMovieLocalData(movieResponseEntity)

    override fun getMovie(): Single<MovieResponseEntity> {
        if (this.connectionManager.isConnected) {
            return remoteDataResource()
        } else {
            return localMovieDataResource.getMovieLocalData()
        }
    }

    override fun getMovieRating(): Single<MovieResponseEntity> {
        return remoteDataRatingResource()
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

    override fun getMovieVideo(id: Int): Single<MovieVideoEntityReponse>
    {
        Log.d("ddd","vinhhoang")
        return remoteVideoDataSource(id)
    }

}