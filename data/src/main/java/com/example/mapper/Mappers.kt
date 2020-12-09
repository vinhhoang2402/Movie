package com.example.mapper

import android.util.Log
import com.example.data.MovieData
import com.example.data.MovieDetailData
import com.example.data.MovieDetailResponseData
import com.example.data.MovieResponseData
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieEntity
import com.example.domain.entity.MovieResponseEntity

//remote
fun MovieData.toMovieEntity() =
    MovieEntity(this.id.toLong(), this.title, this.backdrop_path, this.overview)

fun MovieResponseData.toMovieResponseEntity() = MovieResponseEntity(this.results.map {
    it.toMovieEntity()
})

//movieDetail
fun MovieDetailData.AuthorDetails.toAuthorDetails() = MovieDetailEntity.AuthorDetails(
    this.name,
    this.username, this.avatar_path, this.rating
)

fun MovieDetailData.toMovieDetailEntity() = MovieDetailEntity(
    this.id,
    this.author,
    this.author_details?.toAuthorDetails(),
    this.content,
    this.created_at,
    this.updated_at,
    this.url
)

fun MovieDetailResponseData.toMovieDetailResponseEntity() =
    MovieDetailResponseEntity(this.id, this.results.map {
        Log.d("oooo",it.toMovieDetailEntity().toString())
        it.toMovieDetailEntity()
    })

//local
fun MovieEntity.toMovieData() = MovieData(this.id, this.title, this.backdrop_path, this.overview)

//movieDetail
fun MovieDetailEntity.AuthorDetails.toAuthorDetails() = MovieDetailData.AuthorDetails(
    this.name, this.username, this.avatar_path, this.rating
)

fun MovieDetailEntity.toMovieDetailData(movieId: Long) = MovieDetailData(
    this.id,
    movieId,
    this.author,
    this.author_details?.toAuthorDetails(),
    this.content,
    this.created_at,
    this.updated_at,
    this.url
)



