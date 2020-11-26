package com.example.datasource

import com.example.domain.entity.*
import io.reactivex.Single

interface LocalMovieDataResource {

    fun saveMovieLocalData(movieResponseEntity: MovieResponseEntity)
   // fun getMovieLocalData(): Single<MovieResponseEntity>

}