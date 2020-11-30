package com.example.domain.usecase

import com.example.domain.common.SingleUseCase
import com.example.domain.repository.ConnectionRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GetConnectionStatusUseCase(
    private val connectionRepository: ConnectionRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
) : SingleUseCase<Boolean>(uiThread, executorThread) {
    override fun create(): Single<Boolean> {
        return connectionRepository.getConnectionStatus()
    }
}