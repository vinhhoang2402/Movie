package com.example.domain.usecase

import com.example.domain.common.SingleParamUseCase
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieVideoEntity
import com.example.domain.entity.MovieVideoEntityReponse
import com.example.domain.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import sun.rmi.runtime.Log

class GetMovieVideoUseCase(
    private val movieRepository: MovieRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
): SingleParamUseCase<MovieVideoEntityReponse,Int>(
    uiThread,executorThread
) {
    override fun create(idMovie: Int): Single<MovieVideoEntityReponse> {
        return movieRepository.getMovieVideo(idMovie)
    }

}