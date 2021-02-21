package com.example.domain.usecase

import com.example.domain.common.SingleParamUseCase
import com.example.domain.common.SingleUseCase
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class GetMovieUseCase(
    private val movieRepository: MovieRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
) : SingleParamUseCase<MovieResponseEntity,Int>(uiThread, executorThread) {
    override fun create(page: Int): Single<MovieResponseEntity> {
        return movieRepository.getMovie(page).timeout(3, TimeUnit.SECONDS).retry { times, throwable ->
            times < 5 && throwable is TimeoutException
        }
    }
}