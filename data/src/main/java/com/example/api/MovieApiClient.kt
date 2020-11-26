package com.example.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiClient {
    companion object{
        private const val URL= "https://api.themoviedb.org/3/movie/"
        private val apiInterface: MovieAPI?=null
        fun getMovieService(): MovieAPI{
            if (apiInterface!=null){
                return apiInterface
            }
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(MovieAPI::class.java)
        }
    }
}