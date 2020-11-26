package com.example.movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.movie.R
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val movieViewModelFactory = MovieViewModelFactory(this)
        movieViewModel = ViewModelProvider(this, movieViewModelFactory)
            .get(MovieViewModel::class.java)
        observe()
    }

    private fun observe() {
        movieViewModel.getMovie()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_graph).navigateUp()
    }
}