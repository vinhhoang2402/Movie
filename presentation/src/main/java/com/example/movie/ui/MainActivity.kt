package com.example.movie.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
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
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        Log.d("vinhhoang","vinhhoang")
        Log.d("vikjfksjs","8888888888")
        setContentView(R.layout.activity_main)
        val movieViewModelFactory = MovieViewModelFactory(this)
        movieViewModel = ViewModelProvider(this, movieViewModelFactory)
            .get(MovieViewModel::class.java)
        movieViewModel.getMovie()
        movieViewModel.getMovieRating()
        movieViewModel.video.observe(this, Observer {
            Log.d("video", "bbbbb"+it.toString())
        })
        Log.d("viewModelTest1", movieViewModel.toString())
        movieViewModel.status.observe(this, Observer {

            Toast.makeText(this, "connect $it", Toast.LENGTH_SHORT).show()
        })
        movieViewModel.movieRating.observe(this, Observer {
            Log.d("rating",it.toString())
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_graph).navigateUp()
    }
}