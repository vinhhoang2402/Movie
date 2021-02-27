package com.example.mapper

import android.util.Log
import com.example.data.*
import com.example.domain.entity.*

//remote
fun MovieData.toMovieEntity() =


    MovieEntity(this.id.toLong(), this.title, this.backdrop_path, this.overview,this.vote_average)
//remote
fun GenressData.Genres.toGenresEntity()=GenressEntity.Genres(this.id,this.name)
fun GenressData.toGenresEntity() =
    GenressEntity(this.genres.map {
        it.toGenresEntity()
    })

fun MovieResponseData.toMovieResponseEntity() = MovieResponseEntity(this.results.map {
    it.toMovieEntity()
},this.total_pages)

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
        it.toMovieDetailEntity()
    })


fun MovieVideoData.toMovieVideo()= MovieVideoEntity(
    this.id,this.iso31661,this.iso6391,this.key,
    this.name,this.site,this.size,this.type
)
fun MovieVideoDataReponse.toMovieVideoResponseEntity()= MovieVideoEntityReponse(
    this.results.map {
        Log.d("oooo",it.toMovieVideo().toString())
        it.toMovieVideo()
    }
)

//local
fun MovieEntity.toMovieData() = MovieData(this.id, this.title, this.backdrop_path, this.overview,this.vote_average)

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





