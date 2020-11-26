package com.example.api

import com.example.data.MovieDetailResponseData
import com.example.data.MovieResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface MovieAPI {
    @GET("popular")
    fun getMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ):Single<MovieResponseData>

    @GET("{id_movie}/reviews")
    fun getMovieDetail(
        @Path("id_movie") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ):Single<MovieDetailResponseData>
}