package com.example.dicodingmovie.ui.moviefavorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.databinding.ItemsMovieBinding
import com.example.dicodingmovie.ui.moviefavorite.detail.DetailMovieFavoriteActivity

class MovieFavoriteAdapter : RecyclerView.Adapter<MovieFavoriteAdapter.MoviesViewHolder>() {
    private var listMovies = ArrayList<MovieFavoriteEntity>()

    fun setMovies(movies: List<MovieFavoriteEntity>?) {
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
        fun bind(movie: MovieFavoriteEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemReleaseDate.text = movie.releaseDate.substring(0,4)
                tvItemOverview.text = if(movie.overview.length > 30 ) movie.overview.substring(0,25)+" ...Read More" else movie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieFavoriteActivity::class.java)
                    intent.putExtra(DetailMovieFavoriteActivity.MOVIE_ID, movie.id)
                    intent.putExtra(DetailMovieFavoriteActivity.MOVIE_TITLE, movie.title)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(itemView.resources.getString(R.string.image_base_url, movie.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}