package com.example.dicodingmovie.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.vo.Resource

class DetailMovieViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var movieId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovie(): LiveData<Resource<List<MovieEntity>>>  = appRepository.getMovie(movieId)

    fun getOthersMovies(): LiveData<Resource<List<MovieEntity>>> = appRepository.getOthersMovies(movieId)

}