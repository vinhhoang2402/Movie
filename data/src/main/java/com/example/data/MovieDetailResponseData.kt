package com.example.data

data class MovieDetailResponseData(
    var id: Long,
    var results: MutableList<MovieDetailData> = mutableListOf()
)