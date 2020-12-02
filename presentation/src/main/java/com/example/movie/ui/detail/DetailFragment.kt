package com.example.movie.ui.detail

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.common.DataConstants
import com.example.movie.databinding.FragmentDetailBinding
import com.example.movie.model.MovieData
import com.example.movie.model.MovieDetailResponseData
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class DetailFragment : Fragment() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieViewModelFactory = MovieViewModelFactory(requireContext())
        movieViewModel = ViewModelProvider(this, movieViewModelFactory)
            .get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getSerializable("movie") as MovieData
        movieViewModel.getMovieDetail(movie.id.toInt())
        binding.isLoading = true
        movieViewModel.movieDetail.observe(viewLifecycleOwner, Observer {
            if (it.movieDetails.size > 0) {
                bind(it, movie)
            } else {
                binding.ctDetail.visibility = View.GONE
                showDialog()
            }
        })
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun bind(it: MovieDetailResponseData, movie: MovieData) {
        binding.isLoading = false
        binding.content = it.movieDetails[0].content
        binding.year.text = formatYear(it.movieDetails[0].created_at)
        binding.nameMovie.text = movie.title
        binding.nameMovie.isSelected = true
        Glide.with(requireActivity())
            .load(DataConstants.URL_IMAGE.plus(movie.backdrop_path))
            .into(binding.poster)
    }

    private fun formatYear(year: String): String {
        val newYear = year.substring(0, 4)
        val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
        val date = formatter.parse(newYear)
        return formatter.format(date)
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Alert !")
            setMessage("404 Not found")
            show()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(context, "hhhhhh", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(context, "ggggg", Toast.LENGTH_SHORT).show()
        }
    }
}
