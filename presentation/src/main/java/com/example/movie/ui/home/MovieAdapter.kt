package com.example.movie.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.DataConstants
import com.example.movie.databinding.ItemMovieBinding
import com.example.movie.model.MovieData

class MovieAdapter(
    private val context: Context,
    private val onClick: (MovieData) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(
) {
    private var movieList: List<MovieData> = listOf()

    inner class MovieViewHolder(private val movieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(movieBinding.root) {
        fun onBind(movie: MovieData) {
            movieBinding.movie = movie
            Glide.with(context).load(DataConstants.URL_IMAGE.plus(movie.backdrop_path))
                .into(movieBinding.image)
            movieBinding.ctMovie.setOnClickListener {
                onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemMovieBinding: ItemMovieBinding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    fun set(movie: List<MovieData>) {
        this.movieList = movie
        notifyDataSetChanged()
    }
}