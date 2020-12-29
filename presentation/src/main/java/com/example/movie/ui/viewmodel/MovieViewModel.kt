package com.example.movie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.*
import com.example.movie.common.BaseViewModel
import com.example.movie.mapper.toMovieDetailResponseData
import com.example.movie.mapper.toMovieResponseData
import com.example.movie.mapper.toMovieVideo
import com.example.movie.mapper.toMovieVideoPresentation
import com.example.movie.model.MovieDetailResponseData
import com.example.movie.model.MovieResponseData
import com.example.movie.model.MovieVideo
import com.example.movie.model.MovieVideoResponse
import java.util.concurrent.TimeUnit

class MovieViewModel(
    private val connectionStatusUseCase: GetConnectionStatusUseCase,
    private val movieUseCase: GetMovieUseCase,
    private val movieRatingUseCase: GetMovieRatingUseCase,
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val movieVideoUseCase: GetMovieVideoUseCase
) : BaseViewModel() {
    private val movieResponse = MutableLiveData<MovieResponseData>()
    val movie: LiveData<MovieResponseData> = movieResponse

    private val movieRatingResponse = MutableLiveData<MovieResponseData>()
    val movieRating: LiveData<MovieResponseData> = movieRatingResponse

    private val movieVideo=MutableLiveData<MovieVideoResponse>()
    val video: LiveData<MovieVideoResponse> = movieVideo

    private val movieDetailResponse = MutableLiveData<MovieDetailResponseData>()
    val movieDetail: LiveData<MovieDetailResponseData> = movieDetailResponse

    private val connectionStatus = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = connectionStatus

    init {
        composite.add(
            connectionStatusUseCase().subscribe({
                Log.d("connectionnnnnnnnnnn", it.toString())
                connectionStatus.postValue(it)
                if (it) getMovie()
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
                    movieResponse.postValue(it.toMovieResponseData())
                    Log.d("vvvvvvvv",it.results[0].vote_average.toString())
                }, {
                    Log.d("getMovie", it.toString())
                    showMessage(it.toString())
                })
        )
    }
    fun getMovieRating() {
        composite.add(
            movieRatingUseCase()
                .doOnSubscribe {
                    showLoading(true)
                }
                .doFinally {
                    showLoading(false)
                }
                .subscribe({
                    movieRatingResponse.postValue(it.toMovieResponseData())
                }, {
                    Log.d("getMovie", it.toString())
                    showMessage(it.toString())
                })
        )
    }

    fun getMovieDetail(idMovie: Int) {
        composite.add(
            movieDetailUseCase(idMovie)
                .doOnSubscribe {
                    showLoading(true)
                }
                .doFinally {
                    showLoading(false)
                }
                .subscribe({
                    movieDetailResponse.postValue(it.toMovieDetailResponseData())
                }, {
                    showMessage(it.toString())
                })
        )
    }
    fun getMovieVideo(idMovie: Int){
        composite.add(
            movieVideoUseCase(idMovie)
                .doOnSubscribe {
                    showLoading(true)
                }
                .subscribe({
                    Log.d("vinh",it.toString())
                    movieVideo.postValue(it.toMovieVideoPresentation())
                },{
                    showMessage(it.toString())
                })
        )
    }

}