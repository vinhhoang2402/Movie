package com.example.movie.ui.detail

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.android.synthetic.main.fragment_detail.*
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
        Log.d("viewModelTest3", movieViewModel.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = movieViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getSerializable("movie") as MovieData
        movieViewModel.getMovieDetail(movie.id.toInt())
        movieViewModel.movieDetail.observe(viewLifecycleOwner, Observer {
            Log.d("detail2222", it.movieDetails.size.toString())
            if (it.movieDetails.isNotEmpty()) {
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
//        binding.des.text = it.movieDetails[0].content
//        binding.year.text = it.movieDetails[0].created_at
        binding.nameMovie.text = movie.title
        binding.nameMovie.isSelected = true
        Glide.with(requireActivity())
            .load(DataConstants.URL_IMAGE.plus(movie.backdrop_path))
            .into(binding.poster)
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Alert !")
            setMessage("404 Not found")
            setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                findNavController().navigateUp()
            })
            show()
        }
    }
}
