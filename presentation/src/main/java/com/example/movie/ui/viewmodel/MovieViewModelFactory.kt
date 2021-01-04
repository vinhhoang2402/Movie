package com.example.movie.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.di.Injector

class MovieViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(
                Injector.getMovieConnectUseCase(context),
                Injector.getGenresUseCase(context),
                Injector.getMovieUseCase(context),
                Injector.getMovieRatingUseCase(context),
                Injector.getMovieDetailUseCase(context),
                Injector.getMovieVideoUseCase(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}