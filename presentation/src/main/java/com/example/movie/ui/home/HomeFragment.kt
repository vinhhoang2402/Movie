package com.example.movie.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.model.MovieData
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentHomeBinding
    private val list = mutableListOf<MovieData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieViewModelFactory = MovieViewModelFactory(requireContext())
        movieViewModel = ViewModelProvider(requireActivity(), movieViewModelFactory)
            .get(MovieViewModel::class.java)
    }

    private val onClick: (MovieData) -> Unit = {
        val bundle = bundleOf("movie" to it)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private fun initControls() {
        val adapter: MovieAdapter = MovieAdapter(requireContext(), onClick)
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter = adapter
        binding.isLoading = true
        movieViewModel.movie.observe(requireActivity(), Observer {
            binding.isLoading = false
            list.addAll(it.movies)
            adapter.set(it.movies)
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
        initControls()
        onClick
        searchMovie()
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
}