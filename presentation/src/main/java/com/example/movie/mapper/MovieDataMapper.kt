package com.example.movie.mapper

import com.example.domain.entity.MovieEntity
import com.example.movie.model.MovieData

class MovieDataMapper : Mapper<MovieEntity, MovieData> {
    override fun map(from: MovieEntity): MovieData {
        return MovieData(from.id, from.title, from.backdrop_path, from.overview)
    }
}