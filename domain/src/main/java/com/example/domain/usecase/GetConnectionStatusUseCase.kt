package com.example.domain.usecase

import com.example.domain.common.ObservableUseCase
import com.example.domain.repository.ConnectionRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetConnectionStatusUseCase(
    private val connectionRepository: ConnectionRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
) : ObservableUseCase<Boolean>(uiThread, executorThread) {

    override fun create(): Observable<Boolean> {
        return connectionRepository.getConnectionStatus()

    }
}