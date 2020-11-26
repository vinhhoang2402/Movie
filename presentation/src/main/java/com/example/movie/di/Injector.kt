package com.example.movie.di

import android.content.Context
import com.example.api.MovieApiClient
import com.example.datasource.LocalDataMovieImp
import com.example.datasource.RemoteMovieDataImp
import com.example.datasource.RemoteMovieDataResource
import com.example.db.MovieDatabase
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.domain.usecase.GetMovieUseCase
import com.example.repository.MovieRepositoryImp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Injector {

    @Synchronized
    fun getMovieUseCase(context: Context): GetMovieUseCase {
        return GetMovieUseCase(
            getMovieRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    @Synchronized
    fun getMovieDetailUseCase(context: Context): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(
            getMovieRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepositoryImp(
            getMovieLocalDataSource(context),
            remoteDataSource
        )
    }
    private fun getMovieLocalDataSource(context: Context): LocalDataMovieImp{
        return LocalDataMovieImp(MovieDatabase.getInstance(context))
    }

    private val remoteDataSource: RemoteMovieDataResource by lazy {
        RemoteMovieDataImp(MovieApiClient.getMovieService())
    }
}