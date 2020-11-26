package com.example.movie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.domain.usecase.GetMovieUseCase
import com.example.movie.common.BaseViewModel
import com.example.movie.mapper.MovieDataResponseMapper
import com.example.movie.mapper.toMovieDetailResponseData
import com.example.movie.model.MovieDetailResponseData
import com.example.movie.model.MovieResponseData

class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val movieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel() {
    private val movieResponse = MutableLiveData<MovieResponseData>()
    val movie: LiveData<MovieResponseData> = movieResponse

    private val movieDetailResponse = MutableLiveData<MovieDetailResponseData>()
    val movieDetail: LiveData<MovieDetailResponseData> = movieDetailResponse

    fun getMovie() {
        composite.add(
            movieUseCase()
                .doOnSubscribe { // gọi action được chỉ định ngay khi được subscribe
                    showLoading(true)
                }
                .doFinally {//gọi hành động được chỉ định sau khi Single rơi vào onError()
                    showLoading(false)
                }
                .subscribe({
                    Log.d("aaaaaa",it.toString())
                    movieResponse.value = MovieDataResponseMapper().map(it)
                }, {
                    Log.d("errorrrrrrrrrrrr", "error: ${it.message}")
                    showMessage(it.toString())
                })
        )
    }

    fun getMovieDetail(idMovie: Int) {
        composite.add(
            movieDetailUseCase(idMovie)
                .doOnSubscribe { // gọi action được chỉ định ngay khi được subscribe
                    showLoading(true)
                }
                .doFinally {//gọi hành động được chỉ định sau khi Single rơi vào onError()
                    showLoading(false)
                }
                .subscribe({
                    movieDetailResponse.value = it?.toMovieDetailResponseData()
                }, {
                    showMessage(it.toString())
                })
        )
    }

}