package com.example.domain.usecase

import com.example.domain.common.SingleUseCase
import com.example.domain.entity.GenressEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class GetMovieGenresUseCase(
    private val movieRepository: MovieRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
) : SingleUseCase<GenressEntity>(uiThread, executorThread) {

    override fun create(): Single<GenressEntity> {
        return movieRepository.getGenres().timeout(3, TimeUnit.SECONDS).retry { times, throwable ->
            times < 5 && throwable is TimeoutException
        }
    }
}