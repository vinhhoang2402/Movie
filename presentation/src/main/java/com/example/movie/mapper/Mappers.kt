package com.example.movie.mapper

import android.util.Log
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieDetailResponseEntity
import com.example.domain.entity.MovieEntity
import com.example.domain.entity.MovieResponseEntity
import com.example.movie.model.MovieData
import com.example.movie.model.MovieDetail
import com.example.movie.model.MovieDetailResponseData
import com.example.movie.model.MovieResponseData
import java.text.SimpleDateFormat
import java.util.*


fun MovieEntity.toMovieData()= MovieData(this.id,this.title,this.backdrop_path,this.overview)

fun MovieResponseEntity.toMovieResponseData()=MovieResponseData(this.results.map {
    it.toMovieData()
})
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
