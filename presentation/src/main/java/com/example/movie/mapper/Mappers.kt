package com.example.movie.mapper

import android.util.Log
import com.example.data.GenressData
import com.example.data.MovieVideoData
import com.example.data.MovieVideoDataReponse
import com.example.domain.entity.*
import com.example.mapper.toGenresEntity
import com.example.movie.model.*
import java.text.SimpleDateFormat
import java.util.*

fun GenressEntity.Genres.toGenresPresent()= Genress.Genres(this.id,this.name)
fun GenressEntity.toGenresPresent() =
    Genress(this.genres.map {
        it.toGenresPresent()
    })

fun MovieEntity.toMovieData()= MovieData(this.id,this.title,this.backdrop_path,this.overview,this.vote_average)

fun MovieVideoEntity.toMovieVideo()=MovieVideo(
    this.id,
    this.iso31661,this.iso6391,this.key,this.name,this.site,this.size,this.type
)
fun MovieVideoEntityReponse.toMovieVideoPresentation()= MovieVideoResponse(
    this.results.map {
        it.toMovieVideo()
    }
)
fun MovieResponseEntity.toMovieResponseData()=MovieResponseData(this.results.map {
    it.toMovieData()
},this.total_pages)
fun MovieDetailEntity.AuthorDetails.toAuthorDetails() = MovieDetail.AuthorDetails(
    this.name,
    this.username, this.avatar_path, this.rating
)

fun MovieDetailEntity.toMovieDetail() = MovieDetail(
    this.author,
    this.author_details!!.toAuthorDetails(),
    this.content,
    formatYear(this.created_at),
    this.id,
    this.updated_at,
    this.url
)

private fun formatYear(year: String): String {
    val newYear = year.substring(0, 4)
    val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
    val date = formatter.parse(newYear)
    return formatter.format(date)
}

fun MovieDetailResponseEntity.toMovieDetailResponseData() =
    MovieDetailResponseData(this.results.map {
        it.toMovieDetail()
    })
