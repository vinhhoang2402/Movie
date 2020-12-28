package com.example.movie.ui.detail

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.common.DataConstants
import com.example.movie.databinding.FragmentDetailBinding
import com.example.movie.model.MovieData
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_detail.*


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
        lifecycle.addObserver(binding.youtubePlayerView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getSerializable("movie") as MovieData
        movieViewModel.getMovieDetail(movie.id.toInt())
        movieViewModel.getMovieVideo(movie.id.toInt())
        movieViewModel.video.observe(viewLifecycleOwner, Observer {
            binding.youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {

                override fun onReady(youTubePlayer: YouTubePlayer) {
                    if (lifecycle.currentState==Lifecycle.State.RESUMED){
                        Log.d("ready ","load video")
                        youTubePlayer.loadVideo(it.video[0].key,2f)
                    }else{
                        Log.d("ready ","cue video")
                        youTubePlayer.cueVideo(it.video[0].key,0f)
                    }

                }
            },true)
        })

        movieViewModel.movieDetail.observe(viewLifecycleOwner, Observer {
            Log.d("detail2222", it.movieDetails.size.toString())
            if (it.movieDetails.isNotEmpty()) {
                bind(movie)
            } else {
                //binding.ctDetail.visibility = View.GONE
                //showDialog()
            }
        })
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun bind(movie: MovieData) {
//        Glide.with(requireActivity())
//            .load(DataConstants.URL_IMAGE.plus(movie.backdrop_path))
//            .into(binding.poster)
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

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }
}