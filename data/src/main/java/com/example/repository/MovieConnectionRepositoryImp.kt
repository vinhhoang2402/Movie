package com.example.repository

import com.example.connection.ConnectionManager
import com.example.domain.repository.ConnectionRepository
import io.reactivex.Single

class MovieConnectionRepositoryImp(
    private val connectionManager: ConnectionManager
) : ConnectionRepository {

    override fun getConnectionStatus(): Single<Boolean> {
        return Single.just(connectionManager.isConnected)
    }
}