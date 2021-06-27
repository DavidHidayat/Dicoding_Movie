package com.example.dicodingmovie.ui.moviefavorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingmovie.R
import com.example.dicodingmovie.databinding.FragmentMovieFavoriteBinding
import com.example.dicodingmovie.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MovieFavoriteFragment : Fragment() {
    private lateinit var fragment: FragmentMovieFavoriteBinding
    private lateinit var viewModel: MovieFavoriteViewModel
    private lateinit var movieAdapter: MovieFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragment?.rvMovies)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]
            movieAdapter = MovieFavoriteAdapter()
            fragment?.progressBar?.visibility = View.VISIBLE
            viewModel.getMovies().observe(requireActivity(), { movies ->
                if (movies != null) {
                    fragment?.progressBar?.visibility = View.GONE
                    movieAdapter.submitList(movies)
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
    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieFavoriteEntity = movieAdapter.getSwipedData(swipedPosition)
                movieFavoriteEntity?.let { viewModel.removeFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    movieFavoriteEntity?.let { viewModel.insertFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}