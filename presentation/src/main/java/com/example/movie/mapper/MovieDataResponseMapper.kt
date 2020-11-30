package com.example.movie.mapper

import com.example.domain.entity.MovieResponseEntity
import com.example.movie.model.MovieResponseData

class MovieDataResponseMapper : Mapper<MovieResponseEntity, MovieResponseData> {
    override fun map(from: MovieResponseEntity): MovieResponseData {
        return MovieResponseData(from.results.map {
            MovieDataMapper().map(it)
        }.toMutableList())
    }
}