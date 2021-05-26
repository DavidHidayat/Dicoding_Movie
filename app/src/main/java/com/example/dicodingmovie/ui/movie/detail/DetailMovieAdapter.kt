package com.example.dicodingmovie.ui.movie.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.databinding.ItemsMovieListBinding

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.MovieViewHolder>() {

    private val listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        for (movie in movies) {
            this.listMovies.add(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieListBinding = ItemsMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieListBinding)
    }

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        viewHolder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemsMovieListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            binding.textMovieTitle.text = movie.title
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,  DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.MOVIE_ID, movie.id)
                intent.putExtra(DetailMovieActivity.MOVIE_TITLE, movie.title)
                itemView.context.startActivity(intent)
            }

        }
    }
}