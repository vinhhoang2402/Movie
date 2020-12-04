package com.example.movie.mapper

import android.util.Log
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.movie.model.MovieDetail
import com.example.movie.model.MovieDetailResponseData


fun MovieDetailEntity.AuthorDetails.toAuthorDetails() = MovieDetail.AuthorDetails(
    this.name,
    this.username, this.avatar_path, this.rating
)

fun MovieDetailEntity.toMovieDetail() = MovieDetail(
    this.author,
    this.author_details!!.toAuthorDetails(),
    this.content,
    this.created_at,
    this.id,
    this.updated_at,
    this.url
)

fun MovieDetailResponseEntity.toMovieDetailResponseData() =
    MovieDetailResponseData(this.results.map {
        Log.d("ddddd",it.toString())
        it.toMovieDetail()
    }.toMutableList())
