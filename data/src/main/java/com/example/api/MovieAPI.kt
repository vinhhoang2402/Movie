package com.example.api

import com.example.data.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<GenressData>
    @GET("movie/popular")
    fun getMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieResponseData>

    @GET("movie/top_rated")
    fun getMovieRating(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieResponseData>

    @GET("movie/{id_movie}/reviews")
    fun getMovieDetail(
        @Path("id_movie") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieDetailResponseData>

    @GET("movie/{id_movie}/videos")
    fun getMovieVideo(
        @Path("id_movie") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieVideoDataReponse>
}