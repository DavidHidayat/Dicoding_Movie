package com.example.dicodingmovie.ui.moviefavorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingmovie.databinding.FragmentMovieFavoriteBinding
import com.example.dicodingmovie.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment() {
    private lateinit var fragment: FragmentMovieFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]
            val movieAdapter = MovieFavoriteAdapter()
            fragment?.progressBar?.visibility = View.VISIBLE
            viewModel.getMovies().observe(requireActivity(), { movies ->
                if (movies != null) {
                    fragment?.progressBar?.visibility = View.GONE
                    movieAdapter.setMovies(movies)
                    movieAdapter.notifyDataSetChanged()
                }
            })
            with(fragment?.rvMovies) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }
}