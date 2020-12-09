package com.example.domain.common

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class ObservableUseCase<T>(
    private val uiThread: Scheduler,
    private val executorThread: Scheduler
) {
    operator fun invoke(): Observable<T> {
        return create().subscribeOn(executorThread).observeOn(uiThread)
    }

    abstract fun create(): Observable<T>
}