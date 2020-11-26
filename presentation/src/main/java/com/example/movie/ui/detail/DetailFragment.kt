package com.example.movie.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.common.DataConstants
import com.example.data.MovieDetailData
import com.example.movie.R
import com.example.movie.databinding.FragmentDetailBinding
import com.example.movie.model.MovieData
import com.example.movie.model.MovieDetail
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory

class DetailFragment : Fragment() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentDetailBinding

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
        movieViewModel.getMovieDetail(movie.id)
        binding.isLoading=true
        movieViewModel.movieDetail.observe(viewLifecycleOwner, Observer {
            Log.d("aaa",it.toString())
            if (it.movieDetails.size > 0){
                binding.isLoading=false
                binding.des.text = it.movieDetails[0].content
                binding.nameMovie.text=movie.title
                Glide.with(requireActivity()).load(DataConstants.URL_IMAGE.plus(movie.backdrop_path))
                    .into(binding.poster)
            }
        })
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }
}