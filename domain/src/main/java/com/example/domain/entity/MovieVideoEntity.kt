package com.example.domain.entity

data class MovieVideoEntity(
        val id: String,
        val iso31661: String?=null,
        val iso6391: String?=null,
        val key: String,
        val name: String,
        val site: String,
        val size: Int,
        val type: String
    )