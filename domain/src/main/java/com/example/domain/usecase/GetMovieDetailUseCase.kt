package com.example.domain.usecase

import com.example.domain.common.SingleParamUseCase
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GetMovieDetailUseCase(
    private val movieRepository: MovieRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
) : SingleParamUseCase<MovieDetailResponseEntity,Int>(uiThread,executorThread) {
    override fun create(param: Int): Single<MovieDetailResponseEntity> {
        return movieRepository.getMovieDetail(param).timeout(5,TimeUnit.SECONDS)
    }
}