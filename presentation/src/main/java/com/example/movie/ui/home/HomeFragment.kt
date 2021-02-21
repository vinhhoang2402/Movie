package com.example.movie.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.model.Genress
import com.example.movie.model.MovieData
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.shimmer_home_placeholder_layout.*
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentHomeBinding
    private val list = mutableListOf<MovieData>()
    private var currentPage=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieViewModelFactory = MovieViewModelFactory(requireContext())
        movieViewModel = ViewModelProvider(requireActivity(), movieViewModelFactory)
            .get(MovieViewModel::class.java)
        retainInstance = true
        Log.d("viewModelTest2", movieViewModel.toString())
    }

    private val onClick: (MovieData) -> Unit = {
        val bundle = bundleOf("movie" to it)
        Log.d("bbbbbbb",it.vote_average.toString())
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private val genresOnClick: (Genress.Genres) -> Unit = {
        Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
    }

    private fun initControls() {
        movieViewModel.getMovie(currentPage)
        val adapter = MovieAdapter(requireContext(), onClick)

        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter = adapter
        observeMovie(adapter)
        binding.rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!binding.rvMovie.canScrollVertically(1)){
                    if (currentPage<=20){
                        currentPage+=1
                        movieViewModel.getMovie(currentPage)
                        observeMovie(adapter)
                        Log.d("ccccccc","page"+currentPage)
                    }
                }
            }
        })
        binding.lifecycleOwner = viewLifecycleOwner

        //adapter.set(list)

    }

    private fun observeMovie(adapter : MovieAdapter){
        movieViewModel.movie.observe(viewLifecycleOwner, Observer {
            if ( list.isNullOrEmpty()){
                list.addAll(it.movies)
                adapter.set(list)
                Log.d("fffffff","ccccccccc")
            }else {
                Log.d("fffffff", "fffffffff")
                val oldCount = it.movies.size
                list.removeAll(it.movies)
                Log.d("nnnnn", oldCount.toString())
                list.addAll(it.movies)
                adapter.set(list)
                Log.d("nnnnn", list.size.toString())
                adapter.notifyItemRangeInserted(oldCount, list.size)

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControlsRating()
        initControls()
        initGenres()
        onClick
        genresOnClick
        searchMovie()
    }

    private fun initGenres() {
        //genres
        val genresAdapter: GenresAdapter = GenresAdapter(requireContext(), genresOnClick)
        binding.rvGenres.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rvGenres.setHasFixedSize(true)
        binding.rvGenres.adapter = genresAdapter
        binding.lifecycleOwner = viewLifecycleOwner
        movieViewModel.genres.observe(viewLifecycleOwner, Observer {
            Log.d("genres",it.toString())
            genresAdapter.set(it.genres)
        })
    }

    private fun initControlsRating() {
        //rating
        val adapterRating: MovieRatingAdapter = MovieRatingAdapter(requireContext(), onClick)
        binding.rvPopular.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rvPopular.setHasFixedSize(true)
        binding.rvPopular.adapter = adapterRating
        binding.lifecycleOwner = viewLifecycleOwner
        movieViewModel.movieRating.observe(viewLifecycleOwner, Observer {
            Log.d("ratingggggggg",it.toString())
            adapterRating.set(it.movies)
        })
    }

    private fun searchMovie() {
        binding.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.isNullOrEmpty()) {
                        showSearchData(list)
                    } else {
                        showSearchData(getListDataSearch(query))
                    }
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (query.isNullOrEmpty()) {
                        showSearchData(list)
                    } else {
                        showSearchData(getListDataSearch(query))
                    }
                    return true
                }
            })
            setOnClickListener {
                isIconified = false
            }
        }
    }

    private fun showSearchData(listDataSearch: MutableList<MovieData>) {
        val adapter: MovieAdapter = MovieAdapter(requireContext(), onClick)
        binding.rvMovie.adapter = adapter
        adapter.set(listDataSearch)
    }

    private fun getListDataSearch(query: String): MutableList<MovieData> {
        val listFilter = list.filter {
            it.title.contains(query, true)
        }
        Log.d("vvv", listFilter.toString())
        return listFilter.toMutableList()
    }

    override fun onResume() {
        super.onResume()
//        binding.ctShimmerHome.startShimmerAnimation()
    }

    override fun onPause() {
       // binding.ctShimmerHome.stopShimmerAnimation()
        super.onPause()
    }
}