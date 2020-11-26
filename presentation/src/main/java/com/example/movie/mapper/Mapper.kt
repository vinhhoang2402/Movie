package com.example.movie.mapper

interface Mapper<in FROM, out TO> {
    fun map(from : FROM): TO
}