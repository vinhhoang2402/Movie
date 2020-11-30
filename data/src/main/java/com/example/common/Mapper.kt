package com.example.common

interface Mapper<in FROM, out TO> {
    fun map(from: FROM): TO
}