package com.example.domain.usecase

import com.example.domain.common.SingleUseCase
import com.example.domain.entity.MovieResponseEntity
import com.example.domain.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GetMovieUseCase(
    private val movieRepository: MovieRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
):SingleUseCase<MovieResponseEntity>(uiThread,executorThread) {
    override fun create(): Single<MovieResponseEntity> {
        return movieRepository.getMovie().timeout(5,TimeUnit.SECONDS)
    }

}