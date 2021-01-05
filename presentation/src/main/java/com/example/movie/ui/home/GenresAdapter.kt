package com.example.movie.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.DataConstants
import com.example.movie.databinding.ItemLayoutGenresBinding
import com.example.movie.databinding.ItemMovieBinding
import com.example.movie.model.Genress
import com.example.movie.model.MovieData

class GenresAdapter(private val context: Context,
private val onClick: (Genress.Genres) -> Unit
) : RecyclerView.Adapter<GenresAdapter.MovieViewHolder>(
) {
    private var genresList: List<Genress.Genres> = listOf()

    inner class MovieViewHolder(private val genresBinding: ItemLayoutGenresBinding) :
        RecyclerView.ViewHolder(genresBinding.root) {
        fun onBind(genres: Genress.Genres) {
            genresBinding.genres = genres
            genresBinding.llTypeProject.setOnClickListener {
                onClick(genres)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val genresBinding: ItemLayoutGenresBinding = ItemLayoutGenresBinding.inflate(inflater, parent, false)
        return MovieViewHolder(genresBinding)
    }

    override fun getItemCount(): Int = genresList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(genresList[position])
    }

    fun set(genres: List<Genress.Genres>) {
        this.genresList = genres
        notifyDataSetChanged()
    }
}