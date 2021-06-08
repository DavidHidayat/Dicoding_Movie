package com.example.dicodingmovie.ui.movie.detail

import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.utils.DataDummy

class DetailMovieViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var movieId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        movie = appRepository.getMovie(movieId)
        return movie
    }

    fun getMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        val moviesEntities = appRepository.getAllMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id != movieId) {
                movies.add(movieEntity)
            }
        }
        return movies
    }

}