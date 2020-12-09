package com.example.repository

import android.util.Log
import com.example.connection.ConnectionManager
import com.example.domain.repository.ConnectionRepository
import io.reactivex.Observable


class MovieConnectionRepositoryImp(
    private val connectionManager: ConnectionManager
) : ConnectionRepository {

    override fun getConnectionStatus(): Observable<Boolean> {
        return connectionManager.getConnectionStatus()
    }
}