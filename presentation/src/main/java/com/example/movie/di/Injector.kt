package com.example.movie.di

import android.content.Context
import com.example.api.MovieApiClient
import com.example.connection.ConnectionManager
import com.example.datasource.LocalDataMovieImp
import com.example.datasource.LocalMovieDataResource
import com.example.datasource.RemoteMovieDataImp
import com.example.datasource.RemoteMovieDataResource
import com.example.db.MovieDatabase
import com.example.domain.repository.ConnectionRepository
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.*
import com.example.repository.MovieConnectionRepositoryImp
import com.example.repository.MovieRepositoryImp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Injector {

    @Synchronized
    fun getGenresUseCase(context: Context): GetMovieGenresUseCase {
        return GetMovieGenresUseCase(
            getMovieRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    @Synchronized
    fun getMovieUseCase(context: Context): GetMovieUseCase {
        return GetMovieUseCase(
            getMovieRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    @Synchronized
    fun getMovieRatingUseCase(context: Context): GetMovieRatingUseCase {
        return GetMovieRatingUseCase(
            getMovieRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    @Synchronized
    fun getMovieVideoUseCase(context: Context): GetMovieVideoUseCase {
        return GetMovieVideoUseCase(
            getMovieRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    @Synchronized
    fun getMovieConnectUseCase(context: Context): GetConnectionStatusUseCase {
        return GetConnectionStatusUseCase(
            getConnectionRepository(context),
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

    private fun getConnectionRepository(context: Context): ConnectionRepository {
        return MovieConnectionRepositoryImp(getConnectionManager(context))
    }

    private fun getConnectionManager(context: Context) = ConnectionManager(context)

    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepositoryImp(
            getConnectionManager(context),
            getMovieLocalDataSource(context),
            remoteDataSource
        )
    }

    private fun getMovieLocalDataSource(context: Context): LocalMovieDataResource {
        return LocalDataMovieImp(MovieDatabase.getInstance(context))
    }

    private val remoteDataSource: RemoteMovieDataResource by lazy {
        RemoteMovieDataImp(MovieApiClient.getMovieService())
    }
}