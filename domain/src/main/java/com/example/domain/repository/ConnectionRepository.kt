package com.example.domain.repository

import io.reactivex.Observable
import io.reactivex.Single

interface ConnectionRepository {
    fun getConnectionStatus(): Observable<Boolean>
}