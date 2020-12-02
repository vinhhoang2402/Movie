package com.example.movie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.GetConnectionStatusUseCase
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.domain.usecase.GetMovieUseCase
import com.example.movie.common.BaseViewModel
import com.example.movie.mapper.MovieDataResponseMapper
import com.example.movie.mapper.toMovieDetailResponseData
import com.example.movie.model.MovieDetailResponseData
import com.example.movie.model.MovieResponseData

class MovieViewModel(
    private val connectionStatusUseCase: GetConnectionStatusUseCase,
    private val movieUseCase: GetMovieUseCase,
    private val movieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel() {
    private val movieResponse = MutableLiveData<MovieResponseData>()
    val movie: LiveData<MovieResponseData> = movieResponse

    private val movieDetailResponse = MutableLiveData<MovieDetailResponseData>()
    val movieDetail: LiveData<MovieDetailResponseData> = movieDetailResponse

    private val connectionStatus = MutableLiveData<Boolean>()
    //val status: LiveData<Boolean> = connectionStatus

    init {
        composite.add(
            connectionStatusUseCase().subscribe({
                connectionStatus.postValue(it)
            }, {
                showMessage(it.toString())
            })
        )
    }

    fun getMovie() {
        composite.add(
            movieUseCase()
                .doOnSubscribe {
                    showLoading(true)
                }
                .doFinally {
                    showLoading(false)
                }
                .subscribe({
                    Log.d("aaaaaa", it.toString())
                    movieResponse.postValue(MovieDataResponseMapper().map(it))
                }, {
                    Log.d("errorrrrrrrrrrrr", "error: ${it.message}")
                    showMessage(it.toString())
                })
        )
    }

    fun getMovieDetail(idMovie: Int) {
        connectionStatusUseCase()
        composite.add(
            movieDetailUseCase(idMovie)
                .doOnSubscribe {
                    showLoading(true)
                }
                .doFinally {
                    showLoading(false)
                }
                .subscribe({
                    movieDetailResponse.postValue(it?.toMovieDetailResponseData())
                    Log.d("kkkk", it.results.toString())
                }, {
                    showMessage(it.toString())
                })
        )
    }

}