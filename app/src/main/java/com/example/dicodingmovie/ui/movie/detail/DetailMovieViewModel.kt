package com.example.dicodingmovie.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.vo.Resource

class DetailMovieViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movieById : LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mMovieId ->
        appRepository.getMovieById(mMovieId)
    }


    var getOthersMovies : LiveData<Resource<List<MovieEntity>>> = appRepository.getOthersMovies(movieId.value)

    fun setBookmark() {
        val movieResource = movieById.value
        if (movieResource != null) {
            val favorite = movieResource.data
            if (favorite != null) {
                val movieEntity = favorite
                val newState = !movieEntity.bookmarked
                appRepository.setMovieBookmark(movieEntity, newState)
            }
        }
    }
}