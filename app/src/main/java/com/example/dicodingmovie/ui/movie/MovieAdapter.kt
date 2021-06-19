package com.example.dicodingmovie.ui.movie

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.databinding.ItemsMovieBinding
import com.example.dicodingmovie.ui.movie.detail.DetailMovieActivity
import kotlin.collections.ArrayList


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {
    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size


    class MoviesViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemReleaseDate.text = movie.release_date.substring(0,4)
                tvItemOverview.text = if(movie.overview.length > 30 ) movie.overview.substring(0,25)+" ...Read More" else movie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.MOVIE_ID, movie.id)
                    intent.putExtra(DetailMovieActivity.MOVIE_TITLE, movie.title)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(itemView.resources.getString(R.string.image_base_url, movie.poster_path))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}