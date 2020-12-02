package com.example.movie.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.movie.R
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail.*


class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val movieViewModelFactory = MovieViewModelFactory(this)
        movieViewModel = ViewModelProvider(this, movieViewModelFactory)
            .get(MovieViewModel::class.java)
        movieViewModel.getMovie()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_graph).navigateUp()
    }

}