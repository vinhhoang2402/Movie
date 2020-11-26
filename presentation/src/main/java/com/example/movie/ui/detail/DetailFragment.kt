package com.example.movie.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.common.DataConstants
import com.example.movie.databinding.FragmentDetailBinding
import com.example.movie.model.MovieData
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment : Fragment() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentDetailBinding
    private val currentPage = 1
    private val totalPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieViewModelFactory = MovieViewModelFactory(requireContext())
        movieViewModel = ViewModelProvider(requireActivity(), movieViewModelFactory)
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
        Log.d("vvv", movie.id.toString())
        movieViewModel.getMovieDetail(movie.id)
        binding.isLoading = true
        movieViewModel.movieDetail.observe(viewLifecycleOwner, Observer {
            if (it.movieDetails.size > 0) {
                val year = it.movieDetails[0].created_at
                val newYear = year.substring(0, 4)
                val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
                val date = formatter.parse(newYear)
                val formattedYear = formatter.format(date)
                binding.isLoading = false
                binding.content = it.movieDetails[0].content
                binding.year.text = formattedYear
                binding.nameMovie.text = movie.title
                binding.nameMovie.isSelected = true
                Glide.with(requireActivity())
                    .load(DataConstants.URL_IMAGE.plus(movie.backdrop_path))
                    .into(binding.poster)
            }
        })
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
