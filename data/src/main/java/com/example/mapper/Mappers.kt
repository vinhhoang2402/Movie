package com.example.mapper

import com.example.data.MovieData
import com.example.data.MovieDetailData
import com.example.data.MovieDetailResponseData
import com.example.data.MovieResponseData
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieEntity
import com.example.domain.entity.MovieResponseEntity

fun MovieData.toMovieEntity() = MovieEntity(this.id, this.title, this.backdrop_path, this.overview)
fun MovieResponseData.toMovieResponseEntity() = MovieResponseEntity(this.results.map {
    it.toMovieEntity()
}.toMutableList())

//movieDetail
fun MovieDetailData.AuthorDetails.toAuthorDetails() = MovieDetailEntity.AuthorDetails(
    this.name,
    this.username, this.avatar_path, this.rating
)

fun MovieDetailData.toMovieDetailEntity() = MovieDetailEntity(
    this.id, this.author_details.toAuthorDetails()
    , this.content, this.created_at, this.id, this.updated_at, this.url
)

fun MovieDetailResponseData.toMovieDetailResponseEntity() =
    MovieDetailResponseEntity(this.results.map {
        it.toMovieDetailEntity()
    }.toMutableList())

