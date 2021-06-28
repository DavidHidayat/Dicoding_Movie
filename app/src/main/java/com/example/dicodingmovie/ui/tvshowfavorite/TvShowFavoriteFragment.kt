package com.example.dicodingTV_SHOW.ui.tvshowfavorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.databinding.FragmentTvShowFavoriteBinding
import com.example.dicodingmovie.ui.tvshowfavorite.TvShowFavoriteAdapter
import com.example.dicodingmovie.viewmodel.ViewModelFactory
import com.example.dicodingtvShow.ui.tvshowfavorite.TvShowFavoriteViewModel
import com.google.android.material.snackbar.Snackbar

class TvShowFavoriteFragment : Fragment() {
    private lateinit var fragment: FragmentTvShowFavoriteBinding
    private lateinit var viewModel: TvShowFavoriteViewModel
    private lateinit var tvShowAdapter: TvShowFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = FragmentTvShowFavoriteBinding.inflate(layoutInflater, container, false)
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragment?.rvTvShowsFavorite)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]
            tvShowAdapter = TvShowFavoriteAdapter()
            fragment?.progressBar?.visibility = View.VISIBLE
            viewModel.getTvShows().observe(requireActivity(), { tvShows ->
                if (tvShows != null) {
                    fragment?.progressBar?.visibility = View.GONE
                    tvShowAdapter.submitList(tvShows)
                    tvShowAdapter.notifyDataSetChanged()
                }
            })
            with(fragment?.rvTvShowsFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
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
                val tvShowFavoriteEntity = tvShowAdapter.getSwipedData(swipedPosition)
                tvShowFavoriteEntity?.let { viewModel.removeFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    tvShowFavoriteEntity?.let { viewModel.insertFavorite(it) }
                }
                snackbar.show()
            }
        }
    })

}