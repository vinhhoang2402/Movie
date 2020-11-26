package com.example.movie.common

interface Event {
    fun showMessage(message: String)
    fun showLoading(loading: Boolean)
}