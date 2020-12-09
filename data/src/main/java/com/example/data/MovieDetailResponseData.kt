package com.example.data

data class MovieDetailResponseData(
    var id: Long = 0,
    var results: List<MovieDetailData> = listOf()
)