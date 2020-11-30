package com.example.domain.repository

import io.reactivex.Single

interface ConnectionRepository {
    fun getConnectionStatus(): Single<Boolean>
}